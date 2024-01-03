package binus.skripsi.RatingWeb.service;

import java.io.File;
import java.io.IOException;
import java.rmi.dgc.DGC;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import binus.skripsi.RatingWeb.dto.ExploreMoreList;
import binus.skripsi.RatingWeb.model.Category;
import binus.skripsi.RatingWeb.model.OpenHour;
import binus.skripsi.RatingWeb.model.OpenHourTxn;
import binus.skripsi.RatingWeb.model.Place;
import binus.skripsi.RatingWeb.model.PlacePhoto;
import binus.skripsi.RatingWeb.model.PlacePhotoTxn;
import binus.skripsi.RatingWeb.model.PlaceReview;
import binus.skripsi.RatingWeb.model.PlaceReviewPhoto;
import binus.skripsi.RatingWeb.model.PlaceReviewPhotoTxn;
import binus.skripsi.RatingWeb.model.PlaceReviewTxn;
import binus.skripsi.RatingWeb.model.PlaceTxn;
import binus.skripsi.RatingWeb.model.TicketPrice;
import binus.skripsi.RatingWeb.model.TicketPriceTxn;
import binus.skripsi.RatingWeb.model.User;
import binus.skripsi.RatingWeb.repository.OpenHourRepository;
import binus.skripsi.RatingWeb.repository.OpenHourTxnRepository;
import binus.skripsi.RatingWeb.repository.PlacePhotoRepository;
import binus.skripsi.RatingWeb.repository.PlacePhotoTxnRepository;
import binus.skripsi.RatingWeb.repository.PlaceRepository;
import binus.skripsi.RatingWeb.repository.PlaceReviewPhotoRepository;
import binus.skripsi.RatingWeb.repository.PlaceReviewPhotoTxnRepository;
import binus.skripsi.RatingWeb.repository.PlaceReviewRepository;
import binus.skripsi.RatingWeb.repository.PlaceReviewTxnRepository;
import binus.skripsi.RatingWeb.repository.PlaceTxnRepository;
import binus.skripsi.RatingWeb.repository.TicketPriceRepository;
import binus.skripsi.RatingWeb.repository.TicketPriceTxnRepository;
import binus.skripsi.RatingWeb.repository.UserRepository;

@Service
public class AttractionService {
	
	@Value("${db.photo.path}")
	private String assetsPath;
	
	@Autowired
	PlaceTxnRepository placeTxnRepository;
	@Autowired
	PlacePhotoTxnRepository placePhotoTxnRepository;
	@Autowired
	PlaceRepository placeRepository;
	@Autowired
	PlacePhotoRepository placePhotoRepository;
	@Autowired
	OpenHourRepository openHourRepository;
	@Autowired
	OpenHourTxnRepository openHourTxnRepository;
	@Autowired
	TicketPriceTxnRepository ticketPriceTxnRepository;
	@Autowired
	TicketPriceRepository ticketPriceRepository;
	@Autowired
	PlaceReviewRepository placeReviewRepository;
	@Autowired
	PlaceReviewPhotoRepository placeReviewPhotoRepository;
	@Autowired
	PlaceReviewTxnRepository placeReviewTxnRepository;
	@Autowired
	PlaceReviewPhotoTxnRepository placeReviewPhotoTxnRepository;
	@Autowired
	UserRepository userRepository;
	
	public ArrayList<Object[]> getNewAttraction() {
		ArrayList<Object[]> newAttraction = placeRepository.getNewAttraction();
		return newAttraction;
	}
	
	public ArrayList<Object[]> getTamanHiburanPlace() {
		ArrayList<Object[]> newAttraction = placeRepository.getTamanHiburanPlace();
		return newAttraction;
	}
	
	public ArrayList<Object[]> getPendingAttractionList() {
		ArrayList<Object[]> attractionList = placeTxnRepository.getPendingAttractionData();
		return attractionList;
	}
	
	public ArrayList<Object[]> getPendingReviewList() {
		ArrayList<Object[]> pendingReviewList = placeReviewTxnRepository.getPendingReviewList();
		return pendingReviewList;
	}
	
	public List<Object> getPendingReviewDetail(long id) {
		List<Object> pendingReviewDetail = placeReviewTxnRepository.getPendingDetail(id);
		return pendingReviewDetail;
	}
	
	public List<PlaceReviewPhotoTxn> getPendingReviewPhoto(long id) {
		List<PlaceReviewPhotoTxn> pendingReviewPhoto = placeReviewPhotoTxnRepository.getPendingReviewPhoto(id);
		return pendingReviewPhoto;
	}
	
	public List<PlaceReviewPhoto> getReviewPhoto(long id) {
		List<PlaceReviewPhoto> pendingReviewPhoto = placeReviewPhotoRepository.getReviewPhoto(id);
		return pendingReviewPhoto;
	}
	
	public List<PlaceTxn> getAttractionList() {

		return placeTxnRepository.getAttractionData();
	}
	
	public PlaceTxn getPlaceTxnById(long id) {
        return placeTxnRepository.findById(id).orElseThrow(() ->
            new ValidationException("Invalid Place Txn id: " + id)
        );
    }
	
	public PlaceReviewTxn getPlaceReviewTxnById(long id) {
        return placeReviewTxnRepository.findById(id).orElseThrow(() ->
            new ValidationException("Invalid Place Review Txn id: " + id)
        );
    }
	
	public Place getPlaceById(long id) {
        return placeRepository.findById(id).orElseThrow(() ->
            new ValidationException("Invalid Place id: " + id)
        );
    }
	
	public List<Object>  getAvgStarAndCount(long id) {
        return placeReviewRepository.getAvgStarAndCount(id);
    }
	
	public List<OpenHourTxn> getOpenHourTxnById(long id) {
        return openHourTxnRepository.getOpenHourTxnByPlaceTxnId(id);
    }
	
	public List<TicketPriceTxn> getTicketPriceTxnById(long id) {
        return ticketPriceTxnRepository.getTicketPriceTxnByPlaceTxnId(id);
    }
	
	public List<PlacePhotoTxn> getPlacePhotoTxnById(long id) {
        return placePhotoTxnRepository.getPlacePhotoTxnByPlaceTxnId(id);
    }
    
    public List<OpenHour> getOpenHourById(long id) {
        return openHourRepository.getOpenHourByPlaceId(id);
    }
	
	public List<TicketPrice> getTicketPriceById(long id) {
        return ticketPriceRepository.getTicketPriceByPlaceId(id);
    }
	
	public List<PlacePhoto> getPlacePhotoById(long id) {
        return placePhotoRepository.getPlacePhotoByPlaceId(id);
    }
	
	public Page<Object[]> getPlaceReviewByPlaceId(PageRequest pageRequest, long id) {
        return placeReviewRepository.getPlaceReviewByPlaceId(pageRequest, id);
    }
	
	public Page<Object[]> getAllPhoto(PageRequest pageRequest, long id) {
        return placePhotoRepository.getAllPhoto(pageRequest, id);
    }
	
//	public PlacePhoto getPlacePhotoById(long id) {
//        return placePhotoRepository.findById().orElseThrow(() ->
//            new ValidationException("Invalid Place Photo id: " + id)
//        );
//    }
	
	public String approveAttraction(long id) {
		PlaceTxn placeTxnToApprove = getPlaceTxnById(id);
		placeTxnToApprove.setStatusCd(2);

		List<PlacePhotoTxn> placePhotoTxnToApprove = placePhotoTxnRepository.getPlacePhotoTxnByPlaceTxnId(id);

		for (PlacePhotoTxn data : placePhotoTxnToApprove) {
			data.setStatusCd(2);
			placePhotoTxnRepository.saveAndFlush(data);
			
		}
		
		List<OpenHourTxn> openHourTxnToApprove = openHourTxnRepository.getOpenHourTxnByPlaceTxnId(id);

		for (OpenHourTxn data : openHourTxnToApprove) {
			data.setStatusCd(2);
			openHourTxnRepository.saveAndFlush(data);
		}
		
		List<TicketPriceTxn> ticketPriceTxnToApprove = ticketPriceTxnRepository.getTicketPriceTxnByPlaceTxnId(id);

		for (TicketPriceTxn data : ticketPriceTxnToApprove) {
			data.setStatusCd(2);
			ticketPriceTxnRepository.saveAndFlush(data);
		}
		
		if (placeTxnToApprove.getActionType() == 1) {
			placeRepository.insertTblPlace(id);
			placePhotoRepository.insertTblPlacePhoto(id);
			openHourRepository.insertTblOpenHour(id);
			ticketPriceRepository.insertTblTicketPrice(id);
			placeTxnRepository.saveAndFlush(placeTxnToApprove);
		} if (placeTxnToApprove.getActionType() == 2) {
			SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Timestamp now = new Timestamp(System.currentTimeMillis());
			
			Place place = getPlaceById(placeTxnToApprove.getPlaceId());
			place.setAddress(placeTxnToApprove.getAddress());
			place.setDescription(placeTxnToApprove.getDescription());
			place.setWebsite(placeTxnToApprove.getWebsite());
			place.setUpdateDt(inputFormat.format(now));
			placeRepository.saveAndFlush(place);
			
			for (OpenHourTxn data : openHourTxnToApprove) {
				
				if (placeTxnToApprove.getActionType() == 2) {
					openHourRepository.updateTblOpenHour(data.getOpenHour(), data.getClosedHour(), data.getIsClosed(), data.getDay(), placeTxnToApprove.getPlaceId());
				}
			}
			
			for (TicketPriceTxn data : ticketPriceTxnToApprove) {
				if (placeTxnToApprove.getActionType() == 2) {
					ticketPriceRepository.updateTblTicketPrice(data.getTicketPrice(), data.getDay(), placeTxnToApprove.getPlaceId());
				}
			}
		}
		
		return "success";
	}
	
	public String rejectAttraction(long id) {
		PlaceTxn placeTxnToApprove = getPlaceTxnById(id);
		placeTxnToApprove.setStatusCd(3);

		List<PlacePhotoTxn> placePhotoTxnToApprove = placePhotoTxnRepository.getPlacePhotoTxnByPlaceTxnId(id);

		for (PlacePhotoTxn data : placePhotoTxnToApprove) {
			data.setStatusCd(3);
			placePhotoTxnRepository.saveAndFlush(data);
		}
		
		List<OpenHourTxn> openHourTxnToApprove = openHourTxnRepository.getOpenHourTxnByPlaceTxnId(id);

		for (OpenHourTxn data : openHourTxnToApprove) {
			data.setStatusCd(3);
			openHourTxnRepository.saveAndFlush(data);
		}
		
		List<TicketPriceTxn> ticketPriceTxnToApprove = ticketPriceTxnRepository.getTicketPriceTxnByPlaceTxnId(id);

		for (TicketPriceTxn data : ticketPriceTxnToApprove) {
			data.setStatusCd(3);
			ticketPriceTxnRepository.saveAndFlush(data);
		}
		
		placeTxnRepository.saveAndFlush(placeTxnToApprove);
		
		return "success";
	}
	
	public String approveReview(long id) {
		PlaceReviewTxn placeReviewTxnToApprove = getPlaceReviewTxnById(id);
		placeReviewTxnToApprove.setStatusCd(2);
		
		List<PlaceReviewPhotoTxn> placeReviewPhotoTxnToApprove = placeReviewPhotoTxnRepository.getPendingReviewPhoto(id);

		for (PlaceReviewPhotoTxn data : placeReviewPhotoTxnToApprove) {
			data.setStatusCd(2);
			placeReviewPhotoTxnRepository.saveAndFlush(data);
			
		}
		
		placeReviewRepository.insertReview(id);
		placeReviewPhotoRepository.insertReviewPhoto(id);
		placeReviewTxnRepository.saveAndFlush(placeReviewTxnToApprove);
		
		return "success";
	}
	
	public String rejectReview(long id) {
		PlaceReviewTxn placeReviewTxnToReject = getPlaceReviewTxnById(id);
		placeReviewTxnToReject.setStatusCd(3);
		placeReviewTxnRepository.saveAndFlush(placeReviewTxnToReject);
		return "success";
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public String addAttraction(MultipartFile[] files, PlaceTxn placeTxn, String jamBukaSenin, String jamTutupSenin,
			String jamBukaSelasa, String jamTutupSelasa, String jamBukaRabu, String jamTutupRabu, String jamBukaKamis,
			String jamTutupKamis, String jamBukaJumat, String jamTutupJumat, String jamBukaSabtu, String jamTutupSabtu,
			String jamBukaMinggu, String jamTutupMinggu, String hargaTiketSenin, String hargaTiketSelasa,
			String hargaTiketRabu, String hargaTiketKamis, String hargaTiketJumat, String hargaTiketSabtu,
			String hargaTiketMinggu, String username, String checkboxSenin, String checkboxSelasa, String checkboxRabu, String checkboxKamis, String
			checkboxJumat, String checkboxSabtu, String checkboxMinggu) throws IOException, InterruptedException {
		
		try {
			String date, fileName, filepath = "";
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");

			User user = userRepository.getUserByUsername2(username);
			
			SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Timestamp now = new Timestamp(System.currentTimeMillis());
			
			
			placeTxn.setUser(user);
			placeTxn.setActionType(1);
			placeTxn.setStatusCd(0);
			placeTxn.setInsertDt(inputFormat.format(now));
			placeTxnRepository.save(placeTxn);
			
			for (MultipartFile file : files) {
				if ( !file.isEmpty()) {
					System.out.println("masuk if");
					date = dateFormat.format(new Date());
					
					File directory = new File(System.getProperty("user.dir") + "/src/main/resources/static/attractionAssets/" + placeTxn.getName() + "/");

					if (!directory.exists()) {
						directory.mkdir();
					}
					fileName = placeTxn.getName() + "_" + date + ".jpg";
					filepath = directory + "/" + fileName;

					file.transferTo(new File(filepath));
					
					PlacePhotoTxn placePhotoTxn = new PlacePhotoTxn();
					placePhotoTxn.setPlaceTxn(placeTxn);
					placePhotoTxn.setStatusCd(0);
					placePhotoTxn.setActionType(1);
					placePhotoTxn.setPhotoPath(assetsPath + placeTxn.getName());
					placePhotoTxn.setPhotoName(fileName);
					placePhotoTxnRepository.save(placePhotoTxn);
				}
			}
			
			if (jamBukaSenin.isEmpty()) {
				System.out.println("jam ksoong");
			} else {
				System.out.println("jam terisi");
			}
			
			insertTicketPriceTxn(placeTxn, hargaTiketSenin, hargaTiketSelasa, hargaTiketRabu, hargaTiketKamis,
					hargaTiketJumat, hargaTiketSabtu, hargaTiketMinggu);

			insertOpenHourTxn(placeTxn,jamBukaSenin, jamTutupSenin,
					jamBukaSelasa, jamTutupSelasa, jamBukaRabu, jamTutupRabu, jamBukaKamis, jamTutupKamis, jamBukaJumat,
					jamTutupJumat, jamBukaSabtu, jamTutupSabtu, jamBukaMinggu, jamTutupMinggu,
					checkboxSenin, checkboxSelasa, checkboxRabu, checkboxKamis,
					checkboxJumat, checkboxSabtu, checkboxMinggu);
			
			
		} catch (IOException e) {
			System.out.println(e);
		}
        return "success";
    }
	
	private void insertTicketPriceTxn(PlaceTxn placeTxn, String hargaTiketSenin, String hargaTiketSelasa,
			String hargaTiketRabu, String hargaTiketKamis, String hargaTiketJumat, String hargaTiketSabtu,
			String hargaTiketMinggu) {
	
		TicketPriceTxn ticketPriceTxn = new TicketPriceTxn();
		
			ticketPriceTxn = new TicketPriceTxn();
			ticketPriceTxn.setDay("Senin");
			ticketPriceTxn.setPlaceTxn(placeTxn);
			ticketPriceTxn.setTicketPrice(hargaTiketSenin);
			ticketPriceTxn.setActionType(1);
			ticketPriceTxnRepository.save(ticketPriceTxn);
		
			ticketPriceTxn = new TicketPriceTxn();
			ticketPriceTxn.setDay("Selasa");
			ticketPriceTxn.setPlaceTxn(placeTxn);
			ticketPriceTxn.setTicketPrice(hargaTiketSelasa);
			ticketPriceTxn.setActionType(1);
			ticketPriceTxnRepository.save(ticketPriceTxn);

			ticketPriceTxn = new TicketPriceTxn();
			ticketPriceTxn.setDay("Rabu");
			ticketPriceTxn.setPlaceTxn(placeTxn);
			ticketPriceTxn.setTicketPrice(hargaTiketRabu);
			ticketPriceTxn.setActionType(1);
			ticketPriceTxnRepository.save(ticketPriceTxn);
			
			
			ticketPriceTxn = new TicketPriceTxn();
			ticketPriceTxn.setDay("Kamis");
			ticketPriceTxn.setPlaceTxn(placeTxn);
			ticketPriceTxn.setTicketPrice(hargaTiketKamis);
			ticketPriceTxn.setActionType(1);
			ticketPriceTxnRepository.save(ticketPriceTxn);


			ticketPriceTxn = new TicketPriceTxn();
			ticketPriceTxn.setDay("Jumat");
			ticketPriceTxn.setPlaceTxn(placeTxn);
			ticketPriceTxn.setTicketPrice(hargaTiketJumat);
			ticketPriceTxn.setActionType(1);
			ticketPriceTxnRepository.save(ticketPriceTxn);

			
			ticketPriceTxn = new TicketPriceTxn();
			ticketPriceTxn.setDay("Sabtu");
			ticketPriceTxn.setPlaceTxn(placeTxn);
			ticketPriceTxn.setTicketPrice(hargaTiketSabtu);
			ticketPriceTxn.setActionType(1);
			ticketPriceTxnRepository.save(ticketPriceTxn);

			
			ticketPriceTxn = new TicketPriceTxn();
			ticketPriceTxn.setDay("Minggu");
			ticketPriceTxn.setPlaceTxn(placeTxn);
			ticketPriceTxn.setTicketPrice(hargaTiketMinggu);
			ticketPriceTxn.setActionType(1);
			ticketPriceTxnRepository.save(ticketPriceTxn);
		
	}

	private void insertOpenHourTxn(PlaceTxn placeTxn, String jamBukaSenin, String jamTutupSenin, String jamBukaSelasa,
			String jamTutupSelasa, String jamBukaRabu, String jamTutupRabu, String jamBukaKamis, String jamTutupKamis,
			String jamBukaJumat, String jamTutupJumat, String jamBukaSabtu, String jamTutupSabtu, String jamBukaMinggu,
			String jamTutupMinggu, String checkboxSenin, String checkboxSelasa, String checkboxRabu, String checkboxKamis, String
			checkboxJumat, String checkboxSabtu, String checkboxMinggu) {
		OpenHourTxn openHourTxn = new OpenHourTxn();
			System.out.println("jamBukaSenin " + jamBukaSenin);
			System.out.println("jamTutupSenin " + jamTutupSenin);
			openHourTxn.setDay("Senin");
			openHourTxn.setPlaceTxn(placeTxn);
			openHourTxn.setOpenHour(jamBukaSenin);
			openHourTxn.setClosedHour(jamTutupSenin);
			openHourTxn.setActionType(1);
			System.out.println("checkboxSenin " + checkboxSenin);
			if (StringUtils.hasText(checkboxSenin)) {
				System.out.println("masuk senin");
				openHourTxn.setIsClosed(1);
			}
			
			openHourTxnRepository.save(openHourTxn);
		
		
			openHourTxn = new OpenHourTxn();
			openHourTxn.setDay("Selasa");
			openHourTxn.setPlaceTxn(placeTxn);
			openHourTxn.setOpenHour(jamBukaSelasa);
			openHourTxn.setClosedHour(jamTutupSelasa);
			openHourTxn.setActionType(1);
			System.out.println("checkboxSelasa " + checkboxSelasa);
			if (StringUtils.hasText(checkboxSelasa)) {
				System.out.println("masuk selasa");
				openHourTxn.setIsClosed(1);
			}
			openHourTxnRepository.save(openHourTxn);
		
		
			openHourTxn = new OpenHourTxn();
			openHourTxn.setDay("Rabu");
			openHourTxn.setPlaceTxn(placeTxn);
			openHourTxn.setOpenHour(jamBukaRabu);
			openHourTxn.setClosedHour(jamTutupRabu);
			openHourTxn.setActionType(1);
			if (StringUtils.hasText(checkboxRabu)) {
				openHourTxn.setIsClosed(1);
			}
			openHourTxnRepository.save(openHourTxn);
		
		
			openHourTxn = new OpenHourTxn();
			openHourTxn.setDay("Kamis");
			openHourTxn.setPlaceTxn(placeTxn);
			openHourTxn.setOpenHour(jamBukaKamis);
			openHourTxn.setClosedHour(jamTutupKamis);
			openHourTxn.setActionType(1);
			if (StringUtils.hasText(checkboxKamis)) {
				openHourTxn.setIsClosed(1);
			}
			openHourTxnRepository.save(openHourTxn);
		
		
			openHourTxn = new OpenHourTxn();
			openHourTxn.setDay("Jumat");
			openHourTxn.setPlaceTxn(placeTxn);
			openHourTxn.setOpenHour(jamBukaJumat);
			openHourTxn.setClosedHour(jamTutupJumat);
			openHourTxn.setActionType(1);
			if (StringUtils.hasText(checkboxJumat)) {
				openHourTxn.setIsClosed(1);
			}
			openHourTxnRepository.save(openHourTxn);
		
		
			openHourTxn = new OpenHourTxn();
			openHourTxn.setDay("Sabtu");
			openHourTxn.setPlaceTxn(placeTxn);
			openHourTxn.setOpenHour(jamBukaSabtu);
			openHourTxn.setClosedHour(jamTutupSabtu);
			openHourTxn.setActionType(1);
			if (StringUtils.hasText(checkboxSabtu)) {
				openHourTxn.setIsClosed(1);
			}
			openHourTxnRepository.save(openHourTxn);
		
		
			openHourTxn = new OpenHourTxn();
			openHourTxn.setDay("Minggu");
			openHourTxn.setPlaceTxn(placeTxn);
			openHourTxn.setOpenHour(jamBukaMinggu);
			openHourTxn.setClosedHour(jamTutupMinggu);
			openHourTxn.setActionType(1);
			if (StringUtils.hasText(checkboxMinggu)) {
				openHourTxn.setIsClosed(1);
			}
			openHourTxnRepository.save(openHourTxn);
		
	}
	
	
	public String updateAttraction(MultipartFile[] files, Place place, String jamBukaSenin, String jamTutupSenin,
			String jamBukaSelasa, String jamTutupSelasa, String jamBukaRabu, String jamTutupRabu, String jamBukaKamis,
			String jamTutupKamis, String jamBukaJumat, String jamTutupJumat, String jamBukaSabtu, String jamTutupSabtu,
			String jamBukaMinggu, String jamTutupMinggu, String hargaTiketSenin, String hargaTiketSelasa,
			String hargaTiketRabu, String hargaTiketKamis, String hargaTiketJumat, String hargaTiketSabtu,
			String hargaTiketMinggu, String username, String checkboxSenin, String checkboxSelasa, String checkboxRabu, String checkboxKamis, String
			checkboxJumat, String checkboxSabtu, String checkboxMinggu) throws IOException, InterruptedException {
		
//try {
//			
//			String date, fileName, filepath = "";
//			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
//
			SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Timestamp now = new Timestamp(System.currentTimeMillis());
		
			User user = userRepository.getUserByUsername2(username);
			PlaceTxn placeTxn = new PlaceTxn();
			placeTxn.setName(place.getName());
			placeTxn.setAddress(place.getAddress());
			placeTxn.setDescription(place.getDescription());
			placeTxn.setWebsite(place.getWebsite());
			placeTxn.setUser(user);
			placeTxn.setActionType(2);
			placeTxn.setStatusCd(0);
			placeTxn.setInsertDt(inputFormat.format(now));
			placeTxn.setPlaceId(place.getId());
			placeTxnRepository.save(placeTxn);
//			
//			if (jamBukaSenin.isEmpty()) {
//				System.out.println("jam ksoong");
//			} else {
//				System.out.println("jam terisi");
//			}
//			
			insertTicketPriceTxn(placeTxn, hargaTiketSenin, hargaTiketSelasa, hargaTiketRabu, hargaTiketKamis,
					hargaTiketJumat, hargaTiketSabtu, hargaTiketMinggu);

			insertOpenHourTxn(placeTxn,jamBukaSenin, jamTutupSenin,
					jamBukaSelasa, jamTutupSelasa, jamBukaRabu, jamTutupRabu, jamBukaKamis, jamTutupKamis, jamBukaJumat,
					jamTutupJumat, jamBukaSabtu, jamTutupSabtu, jamBukaMinggu, jamTutupMinggu,
					checkboxSenin, checkboxSelasa, checkboxRabu, checkboxKamis,
					checkboxJumat, checkboxSabtu, checkboxMinggu);
//			
//			
//		} catch (IOException e) {
//			System.out.println(e);
//		}
		
		return "Success";
	}

	public void tulisUlasan(int rateKebersihan, int rateSuasana, int ratePelayanan, Place place, PlaceReviewTxn placeReviewTxn, String username, MultipartFile[] files)
			throws IOException, InterruptedException {
		String date, fileName, filepath = "";
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		
		SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Timestamp now = new Timestamp(System.currentTimeMillis());
		System.out.println("bbbbbbbbb" + placeReviewTxn.getId());
		User user = userRepository.getUserByUsername2(username);
		String title = placeReviewTxn.getTitle();
		String review = placeReviewTxn.getReview();
		
		placeReviewTxn = new PlaceReviewTxn();
		placeReviewTxn.setUser(user);
		placeReviewTxn.setRatingSuasana(rateSuasana);
		placeReviewTxn.setRatingKebersihan(rateKebersihan);
		placeReviewTxn.setRatingPelayanan(ratePelayanan);
		placeReviewTxn.setPlace(getPlaceById(place.getId()));
		placeReviewTxn.setInsertDt(inputFormat.format(now));
		placeReviewTxn.setTitle(title);
		placeReviewTxn.setReview(review);
		placeReviewTxnRepository.save(placeReviewTxn);
		
		for (MultipartFile file : files) {
			if ( !file.isEmpty()) {
				date = dateFormat.format(new Date());
				File directory = new File(System.getProperty("user.dir") + "/src/main/resources/static/attractionAssets/" + place.getName() + "/");

				if (!directory.exists()) {
					directory.mkdir();
				}
				fileName = place.getName() + "_" + date + ".jpg";
				filepath = directory + "/" + fileName;

				file.transferTo(new File(filepath));
				
				PlaceReviewPhotoTxn placeReviewPhotoTxn = new PlaceReviewPhotoTxn();
				
				placeReviewPhotoTxn.setPlaceReviewTxnId(placeReviewTxn);
				placeReviewPhotoTxn.setStatusCd(0);
				placeReviewPhotoTxn.setPhotoPath(assetsPath + place.getName());
				placeReviewPhotoTxn.setPhotoName(fileName);
				placeReviewPhotoTxnRepository.save(placeReviewPhotoTxn);
			}
		}

	}

	public Page<Object[]> getAttractionListByCategory(PageRequest pageRequest, long id) {
		// TODO Auto-generated method stub
		return placeRepository.getAttractionByCategory(pageRequest, id);
	}
}
