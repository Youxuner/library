package com.jpa.library.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jpa.library.bean.Libro;
import com.jpa.library.bean.LibroDTO;
import com.jpa.library.bean.Magazzino;
import com.jpa.library.bean.MagazzinoDTO;
import com.jpa.library.dao.LibroDAORepository;

@Transactional(propagation = Propagation.REQUIRED)
@Service("LibroService")
public class LibroServiceImpl implements LibroService {

	@Autowired
	private LibroDAORepository libroDAO;
	
	@Override
	public HttpStatus registraNuovoLibro(Libro libro) {
		
		if (!libro.passCheck())
			return HttpStatus.BAD_REQUEST;
		
		if (libroDAO.existsById(libro.getIsbn()))
			return HttpStatus.CONFLICT;
		
		libroDAO.save(libro);
		approvvigionaLibro(libro.getIsbn(), 50);
		
		return HttpStatus.CREATED;
	}

	@Override
	public LibroDTO modificaDatiLibro(Libro libro) {
		Libro lib = libroDAO.save(libro);
		if (lib.getnCopie() < 10)
			approvvigionaLibro(lib.getIsbn(), 50);
		
		return new LibroDTO(lib);
	}

	@Override
	public HttpStatus eliminaLibro(int isbn) {
		Libro lib = libroDAO.getById(isbn);
		if (lib.getMagazzinoState().stream().anyMatch(m -> m.getStatoStock().equals("processato")))
			return HttpStatus.CONFLICT;
		
		libroDAO.delete(lib);
		return HttpStatus.OK;
	}

	@Override
	public List<LibroDTO> selezionaLibriPerAutore(int idAutore) {
		return libroDAO.findAll().stream().filter(libro -> libro.getAutore() != null && libro.getAutore().getIdAutore() == idAutore)
			.map(libro -> new LibroDTO(libro)).collect(Collectors.toList());
	}

	@Override
	public LibroDTO leggiDatiLibro(int isbn) {
		Libro libro = null;
		if (libroDAO.existsById(isbn))
			libro = libroDAO.getById(isbn);
		return libro == null ? null: new LibroDTO(libro);
	}

	@Override
	public List<LibroDTO> selezionaTuttiLibri(int offset, int page) {
		if (offset == 0)
			return libroDAO.findAll().stream()
					.sorted((l1, l2) -> l1.getTitolo().compareTo(l2.getTitolo()))
					.map(libro -> new LibroDTO(libro)).collect(Collectors.toList());
		
		int n = (page - 1) * offset;
		return libroDAO.findAll().stream()
				.sorted((l1, l2) -> l1.getTitolo().compareTo(l2.getTitolo()))
				.skip(n).limit(offset).map(libro -> new LibroDTO(libro))
				.collect(Collectors.toList());
	}

	@Override
	public Map<String, Long> statistichePerNazione() {
		return libroDAO.findAll().stream()
				.collect(Collectors.groupingBy(
						libro -> libro.getAutore() == null ? "null" : libro.getAutore().getNazioneResidenza(),
								Collectors.counting()
				));
	}

	@Override
	public void approvvigionaLibro(int isbn, int nCopie) {
		Libro lib = libroDAO.getById(isbn);
		int qty = nCopie;
		List<Magazzino> listMagaz = lib.getMagazzinoState();
		Magazzino magaz = null;
		
		// se non e' vuota, allora...
		if (!listMagaz.isEmpty())
		{
			if (listMagaz.stream().anyMatch(m -> m.getStatoStock().equalsIgnoreCase("processato")))
				return;
			
			magaz = listMagaz.stream().filter(m -> m.getStatoStock().equalsIgnoreCase("disponibile"))
					.findFirst().orElse(null);
			if (magaz != null)
				qty -= magaz.getQty();
			if (qty <= 0)
				return;
			
			magaz = listMagaz.stream().filter(m -> m.getStatoStock().equalsIgnoreCase("richiesto"))
					.findFirst().orElse(null);
			// se esiste gia' una richiesta e la quantita' richiesta e' maggiore o uguale di
			// quello che stiamo per fare, return;
			if (magaz != null)
			{
				if (magaz.getQty() >= qty)
					return;
				
				magaz.setQty(qty);
				libroDAO.save(lib);
				return;
			}
		}
		
		// se e' vuota oppure non contiene la richiesta, allora...
		magaz = new Magazzino();
		
		magaz.setStatoStock("richiesto");
		magaz.setQty(qty);
		listMagaz.add(magaz);
		libroDAO.save(lib);
		return;
	}

	@Override
	public HttpStatus cambiaStatoRichiesta(int isbn, String stato) {
		Libro lib = libroDAO.getById(isbn);
		Magazzino m = null;
		Magazzino mDisp = lib.getMagazzinoState().stream()
				.filter(maga -> maga.getStatoStock().equalsIgnoreCase("disponibile"))
				.findFirst().orElse(null);
		HttpStatus status = HttpStatus.OK;
		
		switch (stato)
		{
			case "processato":
				m = lib.getMagazzinoState().stream()
					.filter(maga -> maga.getStatoStock().equalsIgnoreCase("richiesto"))
					.findFirst().orElse(null);
				if (m != null)
					m.setStatoStock(stato);
				else
					status = HttpStatus.BAD_REQUEST;
				break;
			case "disponibile":
				m = lib.getMagazzinoState().stream()
				.filter(maga -> maga.getStatoStock().equalsIgnoreCase("processato"))
				.findFirst().orElse(null);
				if (m != null)
				{
					if (mDisp != null)
					{
						mDisp.setQty(mDisp.getQty() + m.getQty());
						lib.getMagazzinoState().remove(m);
					}
					else
					{
						m.setStatoStock(stato);
					}
				}
				else
					status = HttpStatus.BAD_REQUEST;
				break;
			default:
				status = HttpStatus.NOT_ACCEPTABLE;
				break;
		}
		
		libroDAO.save(lib);
		return status;
	}

	@Override
	public List<MagazzinoDTO> leggiInfoStock(int isbn) {
		return libroDAO.getById(isbn).getMagazzinoState().stream()
				.map(m -> new MagazzinoDTO(m)).collect(Collectors.toList());
	}

	@Override
	public HttpStatus chiudiRichiesta(int isbn) {
		Libro lib = libroDAO.getById(isbn);
		Magazzino m = lib.getMagazzinoState().stream()
				.filter(maga -> maga.getStatoStock().equalsIgnoreCase("disponibile"))
				.findFirst().orElse(null);
		
		if (m == null)
			return HttpStatus.BAD_REQUEST;
		
		lib.setnCopie(lib.getnCopie() + m.getQty());
		lib.getMagazzinoState().remove(m);
		libroDAO.save(lib);
		return HttpStatus.OK;
	}

}
