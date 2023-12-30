package binus.skripsi.RatingWeb.controller;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
import binus.skripsi.RatingWeb.repository.PlaceRepository;
import binus.skripsi.RatingWeb.service.AttractionService;
import binus.skripsi.RatingWeb.service.CategoryService;
import binus.skripsi.RatingWeb.service.UserService;

@Controller
@RequestMapping("")
public class AttractionController {
	@Autowired
	AttractionService attractionService;
	@Autowired
	CategoryService categoryService;
	@Autowired
	UserService userService;
	@Autowired
	PlaceRepository placeRepository;
	
	@RequestMapping("/admin/pending-attraction-list")
	public String viewPendingAttraction(Model model, RedirectAttributes redirectAttributes) {
		ArrayList<Object[]> result = attractionService.getPendingAttractionList();
		model.addAttribute("pendingAttraction", result);
		
		 if (redirectAttributes.containsAttribute("successMessage")) {
		        model.addAttribute("successMessage", redirectAttributes.getAttribute("successMessage"));
		    }
		return "admin/PendingAttractionList";
	}
	
	@RequestMapping("/admin/pending-attraction-detail/{id}")
	public String viewPendingAttractionDetail(@PathVariable("id") long id, Model model) {
		Place placeDetail = null;
		List<OpenHour> openHour = null;
		List<TicketPrice> ticketPrice = null;
		PlaceTxn result = attractionService.getPlaceTxnById(id);
		if(result.getActionType() == 2) {
			placeDetail = attractionService.getPlaceById(result.getPlaceId());
			openHour = attractionService.getOpenHourById(result.getPlaceId());
			ticketPrice = attractionService.getTicketPriceById(result.getPlaceId());
		}

		List<TicketPriceTxn> ticketPriceTxnResult = attractionService.getTicketPriceTxnById(id);
		List<OpenHourTxn> openHourTxnResult = attractionService.getOpenHourTxnById(id);
		List<PlacePhotoTxn> placePhotoTxnResult = attractionService.getPlacePhotoTxnById(id);
		model.addAttribute("placeTxn", result);
		model.addAttribute("ticketPriceTxn", ticketPriceTxnResult);
		model.addAttribute("openHourTxn", openHourTxnResult);
		model.addAttribute("placePhotoTxn", placePhotoTxnResult);
		model.addAttribute("place", placeDetail);
		model.addAttribute("openHour", openHour);
		model.addAttribute("ticketPrice", ticketPrice);
		return "admin/PendingAttractionDetail";
	}
	
	@PostMapping("/admin/pending-attraction-detail/approve")
	public String approvePendingAttractionDetail(@RequestParam(name="approve_button", required = false) String approveButton, @RequestParam(name="reject_button", required = false) String rejectButton, @Valid PlaceTxn placeTxn,
			RedirectAttributes redirectAttributes) {
		String url = "";
		if (approveButton != null) {
			String response= attractionService.approveAttraction(placeTxn.getId());
			redirectAttributes.addFlashAttribute("successMessage", "Data Tempat Wisata berhasil diapprove");
			url = "redirect:/admin/pending-attraction-list";
		} else if (rejectButton != null) {
			String response= attractionService.rejectAttraction(placeTxn.getId());
			redirectAttributes.addFlashAttribute("successMessage", "Data Tempat Wisata berhasil direject");
			url = "redirect:/admin/pending-attraction-list";
		}
		
//		String response= attractionService.approveAttraction(placeTxn.getId());
		return url;
	}
	
	@RequestMapping("/admin/pending-review-list")
	public String viewPendingReview(Model model, @RequestParam(name = "approve", required = false) boolean approve,
		    @RequestParam(name = "reject", required = false) boolean reject,
		    RedirectAttributes redirectAttributes) {
		ArrayList<Object[]> result = attractionService.getPendingReviewList();
		model.addAttribute("pendingReview", result);
		
		 if (redirectAttributes.containsAttribute("successMessage")) {
		        model.addAttribute("successMessage", redirectAttributes.getAttribute("successMessage"));
		    }
		return "admin/PendingReviewList";
	}
	
	@RequestMapping("/admin/pending-review-detail/{id}")
	public String viewPendingReviewDetail(@PathVariable("id") long id, Model model, HttpSession session) {
		List<Object> result = attractionService.getPendingReviewDetail(id);
		List<PlaceReviewPhotoTxn> resultPhoto = attractionService.getPendingReviewPhoto(id);
		PlaceReviewTxn placeReviewTxn = attractionService.getPlaceReviewTxnById(id);
		model.addAttribute("pendingReview", result);
		model.addAttribute("placeReviewTxn", placeReviewTxn);
		model.addAttribute("placeReviewPhoto", resultPhoto);
		
		return "admin/PendingReviewDetail";
	}
	
	@PostMapping("/admin/pending-review-detail/approve")
	public String approvePendingReviewDetail(@RequestParam(name="approve_button", required = false) String approveButton, @RequestParam(name="reject_button", required = false) String rejectButton, @Valid PlaceReviewTxn placeReviewTxn,
			RedirectAttributes redirectAttributes) {
		System.out.println("placeReviewTxnplaceReviewTxnplaceReviewTxn" + placeReviewTxn.getId());
		String url = "";
		if (approveButton != null) {
			String response= attractionService.approveReview(placeReviewTxn.getId());
			redirectAttributes.addFlashAttribute("successMessage", "Data Rating dan Ulasan berhasil diapprove");
			url = "redirect:/admin/pending-review-list";
		} else if (rejectButton != null) {
			String response= attractionService.rejectReview(placeReviewTxn.getId());
			redirectAttributes.addFlashAttribute("successMessage", "Data Rating dan Ulasan berhasil direject");
			url = "redirect:/admin/pending-review-list";
		}
		return url;
	}
	
	@RequestMapping("/user/home")
	public String viewAttraction(Model model, RedirectAttributes redirectAttributes) {

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails)principal).getUsername();
		ArrayList<Object[]> newPlace = attractionService.getNewAttraction();
		ArrayList<Object[]> tamanHiburanPlace = attractionService.getTamanHiburanPlace();
		userService.getDetailUser(username);
		System.out.println(username + " username");
		if (redirectAttributes.containsAttribute("addAttractionMessage")) {
	        model.addAttribute("successMessage", redirectAttributes.getAttribute("addAttractionMessage"));
	    }
		model.addAttribute("newPlace", newPlace);
		model.addAttribute("tamanHiburanPlace", tamanHiburanPlace);
		
		return "Home";
	}
	
	@GetMapping("/user/add-attraction")
	public String viewAddAttraction(Model model) {
		List<Category> category = categoryService.getAllCategory();
		
		model.addAttribute("openHourTxn", new OpenHour());
		model.addAttribute("placeTxn", new PlaceTxn());
		model.addAttribute("placePhotoTxn", new PlacePhotoTxn());
		model.addAttribute("category", category);
		return "AddAttraction";
	}

	@PostMapping("/user/add-attraction")
	public String addAttraction(@RequestParam(name = "hargaTiketSenin", required = false) String hargaTiketSenin,
			@RequestParam(name = "hargaTiketSelasa", required = false) String hargaTiketSelasa,
			@RequestParam(name = "hargaTiketRabu", required = false) String hargaTiketRabu,
			@RequestParam(name = "hargaTiketKamis", required = false) String hargaTiketKamis,
			@RequestParam(name = "hargaTiketJumat", required = false) String hargaTiketJumat,
			@RequestParam(name = "hargaTiketSabtu", required = false) String hargaTiketSabtu,
			@RequestParam(name = "hargaTiketMinggu", required = false) String hargaTiketMinggu,
			@RequestParam(name = "jamBukaSenin", required = false) String jamBukaSenin,
			@RequestParam(name = "jamTutupSenin", required = false) String jamTutupSenin,
			@RequestParam(name = "jamBukaSelasa", required = false) String jamBukaSelasa,
			@RequestParam(name = "jamTutupSelasa", required = false) String jamTutupSelasa,
			@RequestParam(name = "jamBukaRabu", required = false) String jamBukaRabu,
			@RequestParam(name = "jamTutupRabu", required = false) String jamTutupRabu,
			@RequestParam(name = "jamBukaKamis", required = false) String jamBukaKamis,
			@RequestParam(name = "jamTutupKamis", required = false) String jamTutupKamis,
			@RequestParam(name = "jamBukaJumat", required = false) String jamBukaJumat,
			@RequestParam(name = "jamTutupJumat", required = false) String jamTutupJumat,
			@RequestParam(name = "jamBukaSabtu", required = false) String jamBukaSabtu,
			@RequestParam(name = "jamTutupSabtu", required = false) String jamTutupSabtu,
			@RequestParam(name = "jamBukaMinggu", required = false) String jamBukaMinggu,
			@RequestParam(name = "jamTutupMinggu", required = false) String jamTutupMinggu,
			@RequestParam(name = "checkboxSenin", required = false) String checkboxSenin,
			@RequestParam(name = "checkboxSelasa", required = false) String checkboxSelasa,
			@RequestParam(name = "checkboxRabu", required = false) String checkboxRabu,
			@RequestParam(name = "checkboxKamis", required = false) String checkboxKamis,
			@RequestParam(name = "checkboxJumat", required = false) String checkboxJumat,
			@RequestParam(name = "checkboxSabtu", required = false) String checkboxSabtu,
			@RequestParam(name = "checkboxMinggu", required = false) String checkboxMinggu,
			@RequestParam("files") MultipartFile[] files, PlaceTxn placeTxn, Model model,
			RedirectAttributes redirectAttributes)
			throws IOException, InterruptedException {

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails)principal).getUsername();
		System.out.println("checkboxSenin " + checkboxSenin);
		String errorMessage = attractionService.addAttraction(files, placeTxn, jamBukaSenin, jamTutupSenin,
				jamBukaSelasa, jamTutupSelasa, jamBukaRabu, jamTutupRabu, jamBukaKamis, jamTutupKamis, jamBukaJumat,
				jamTutupJumat, jamBukaSabtu, jamTutupSabtu, jamBukaMinggu, jamTutupMinggu, hargaTiketSenin,
				hargaTiketSelasa, hargaTiketRabu, hargaTiketKamis, hargaTiketJumat, hargaTiketSabtu, hargaTiketMinggu,
				username, checkboxSenin, checkboxSelasa, checkboxRabu, checkboxKamis,
				checkboxJumat, checkboxSabtu, checkboxMinggu);
		redirectAttributes.addFlashAttribute("addAttractionMessage", "Data Tempat Wisata berhasil diajukan. Mohon tunggu konfirmasi dari admin untuk proses persetujuan.");
        return "redirect:/user/home";
	}
	
	@RequestMapping("/user/attraction-detail/{id}")
	public String viewAddCategory2(@PathVariable("id") long id, Model model, @RequestParam(defaultValue = "1") int page,
			RedirectAttributes redirectAttributes) {
		
		
		Place result = attractionService.getPlaceById(id);
		List<TicketPrice> ticketPriceResult = attractionService.getTicketPriceById(id);
		List<OpenHour> openHourResult = attractionService.getOpenHourById(id);
		List<PlacePhoto> placePhoto = attractionService.getPlacePhotoById(id);
		List<Category> category = categoryService.getAllCategory();
		List<Object> countStar = attractionService.getAvgStarAndCount(id);
		List<PlaceReviewPhoto> resultPhoto = attractionService.getReviewPhoto(id);
		int pageSize=5;
		Page<Object[]> placeReviewResult = attractionService.getPlaceReviewByPlaceId(PageRequest.of(page-1, pageSize), id);
		model.addAttribute("ids", id);
		model.addAttribute("place", result);
		model.addAttribute("ticketPrice", ticketPriceResult);
		model.addAttribute("openHour", openHourResult);
		model.addAttribute("placePhoto", placePhoto);
		model.addAttribute("category", category);
		model.addAttribute("placeReview", placeReviewResult);
		model.addAttribute("countStar", countStar);
		model.addAttribute("aaaa", "bbbb");
		model.addAttribute("currentPage", page);
		model.addAttribute("placeReviewPhoto", resultPhoto);
//		placeReviewResult.add(new Object[] {"java", "aaaa"});
		
		for (Object[] objArray : placeReviewResult) {
			String insertDate = objArray[6].toString();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			 
			LocalDateTime localDateTime = LocalDateTime.parse(insertDate, formatter);
			String timeAgos = calculateTimeAgo(localDateTime);
			objArray[7]= timeAgos;
		}
		
		if (redirectAttributes.containsAttribute("reviewMessage")) {
	        model.addAttribute("reviewMessage", redirectAttributes.getAttribute("reviewMessage"));
	    } else if (redirectAttributes.containsAttribute("editAttractionMessage")) {
	        model.addAttribute("editAttractionMessage", redirectAttributes.getAttribute("editAttractionMessage"));
	    }
		
		return "AttractionDetail";
	}
	
	@RequestMapping("/user/attraction-action")
	public String attractionActionRepointing(@RequestParam(name="tulis_ulasan", required = false) String tulisUlasan, @RequestParam(name="edit_informasi", required = false) String editInformasi, @Valid Place place) {
		System.out.println("aaaaaaaaa");
		System.out.println("tulis_ulasan" + tulisUlasan);
		System.out.println("edit_informasi" + editInformasi + " " + place.getId());
		if (tulisUlasan != null) {
			return "redirect:/user/tulis-ulasan/" + place.getId();
		} else if (editInformasi != null) {
			return "redirect:/user/edit-attraction/" + place.getId();
		}
		
		return null;
	}
	
	@RequestMapping("/user/tulis-ulasan/{id}")
	public String tulisUlasan(@PathVariable("id") long id, Model model) {
		Place result = attractionService.getPlaceById(id);
		model.addAttribute("place", result);
		model.addAttribute("placeReviewTxn", new PlaceReviewTxn());
		
		return "GiveRating";
	}
	
	@PostMapping("/user/tulis-ulasan")
	public String tulisUlasan2(@RequestParam(name = "rateKebersihan", required = false) int rateKebersihan, 
			@RequestParam(name = "rateSuasana", required = false) int rateSuasana, 
			@RequestParam(name = "ratePelayanan", required = false) int ratePelayanan, Place place, PlaceReviewTxn placeReviewTxn,
			RedirectAttributes redirectAttributes, @RequestParam("files") MultipartFile[] files) throws IOException, InterruptedException {
		
		System.out.println("cccccccc" + placeReviewTxn);
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails)principal).getUsername();
		
		redirectAttributes.addFlashAttribute("reviewMessage", "Data Rating dan Ulasan berhasil diajukan. Mohon tunggu konfirmasi dari admin untuk proses persetujuan.");
		
		attractionService.tulisUlasan(rateKebersihan, rateSuasana, ratePelayanan, place, placeReviewTxn, username, files);
		return "redirect:/user/attraction-detail/" + place.getId();
	}
	
	@RequestMapping("/user/edit-attraction/{id}")
	public String editAttraction(@PathVariable("id") long id, Model model) {
		Place result = attractionService.getPlaceById(id);
		List<Category> category = categoryService.getAllCategory();
		List<TicketPrice> ticketPriceResult = attractionService.getTicketPriceById(id);
		List<OpenHour> openHourResult = attractionService.getOpenHourById(id);
		List<PlacePhoto> placePhoto = attractionService.getPlacePhotoById(id);
		model.addAttribute("place", result);
		model.addAttribute("placeTxn", new PlaceTxn());
		model.addAttribute("ticketPrice", ticketPriceResult);
		model.addAttribute("openHour", openHourResult);
		model.addAttribute("placePhoto", placePhoto);
		model.addAttribute("category", category);
		return "EditAttraction";
	}
	
	@PostMapping("/user/edit-attraction")
	public String updateAttraction(@RequestParam(name = "hargaTiketSenin", required = false) String hargaTiketSenin,
			@RequestParam(name = "hargaTiketSelasa", required = false) String hargaTiketSelasa,
			@RequestParam(name = "hargaTiketRabu", required = false) String hargaTiketRabu,
			@RequestParam(name = "hargaTiketKamis", required = false) String hargaTiketKamis,
			@RequestParam(name = "hargaTiketJumat", required = false) String hargaTiketJumat,
			@RequestParam(name = "hargaTiketSabtu", required = false) String hargaTiketSabtu,
			@RequestParam(name = "hargaTiketMinggu", required = false) String hargaTiketMinggu,
			@RequestParam(name = "jamBukaSenin", required = false) String jamBukaSenin,
			@RequestParam(name = "jamTutupSenin", required = false) String jamTutupSenin,
			@RequestParam(name = "jamBukaSelasa", required = false) String jamBukaSelasa,
			@RequestParam(name = "jamTutupSelasa", required = false) String jamTutupSelasa,
			@RequestParam(name = "jamBukaRabu", required = false) String jamBukaRabu,
			@RequestParam(name = "jamTutupRabu", required = false) String jamTutupRabu,
			@RequestParam(name = "jamBukaKamis", required = false) String jamBukaKamis,
			@RequestParam(name = "jamTutupKamis", required = false) String jamTutupKamis,
			@RequestParam(name = "jamBukaJumat", required = false) String jamBukaJumat,
			@RequestParam(name = "jamTutupJumat", required = false) String jamTutupJumat,
			@RequestParam(name = "jamBukaSabtu", required = false) String jamBukaSabtu,
			@RequestParam(name = "jamTutupSabtu", required = false) String jamTutupSabtu,
			@RequestParam(name = "jamBukaMinggu", required = false) String jamBukaMinggu,
			@RequestParam(name = "jamTutupMinggu", required = false) String jamTutupMinggu,
			@RequestParam(name = "checkboxSenin", required = false) String checkboxSenin,
			@RequestParam(name = "checkboxSelasa", required = false) String checkboxSelasa,
			@RequestParam(name = "checkboxRabu", required = false) String checkboxRabu,
			@RequestParam(name = "checkboxKamis", required = false) String checkboxKamis,
			@RequestParam(name = "checkboxJumat", required = false) String checkboxJumat,
			@RequestParam(name = "checkboxSabtu", required = false) String checkboxSabtu,
			@RequestParam(name = "checkboxMinggu", required = false) String checkboxMinggu,
			@RequestParam("files") MultipartFile[] files, Place place, Model model,
			RedirectAttributes redirectAttributes) throws IOException, InterruptedException {
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails)principal).getUsername();
		attractionService.updateAttraction(files, place, jamBukaSenin, jamTutupSenin,
				jamBukaSelasa, jamTutupSelasa, jamBukaRabu, jamTutupRabu, jamBukaKamis, jamTutupKamis, jamBukaJumat,
				jamTutupJumat, jamBukaSabtu, jamTutupSabtu, jamBukaMinggu, jamTutupMinggu, hargaTiketSenin,
				hargaTiketSelasa, hargaTiketRabu, hargaTiketKamis, hargaTiketJumat, hargaTiketSabtu, hargaTiketMinggu, username, 
				checkboxSenin, checkboxSelasa, checkboxRabu, checkboxKamis, checkboxJumat, checkboxSabtu, checkboxMinggu);
		System.out.println("dddd " + place.getId());
		redirectAttributes.addFlashAttribute("editAttractionMessage", "Data Edit Tempat Wisata berhasil diajukan. Mohon tunggu konfirmasi dari admin untuk proses persetujuan.");
		
		return "redirect:/user/attraction-detail/" + place.getId();
	}

	
	@RequestMapping("/user/explore-more/{id}")
	public String viewExploreMoreByCategory(@PathVariable("id") long id,@RequestParam(defaultValue = "1") int page, Model model) {
		
		List<Category> category = categoryService.getAllCategory();
		model.addAttribute("category", category);
		
		int pageSize = 9; // Set the number of items per page
        Page<Object[]> entityPage = attractionService.getAttractionListByCategory(PageRequest.of(page-1, pageSize), id);
        model.addAttribute("entities", entityPage);
        model.addAttribute("ids", id);
        model.addAttribute("currentPage", page);
        System.out.println(entityPage);
		return "CategoryDetail";
	}
	
	@RequestMapping("/user/gallery-photo/{id}")
	public String viewGalleryPhoto(@PathVariable("id") long id, Model model, @RequestParam(defaultValue = "1") int page) {
		Place result = attractionService.getPlaceById(id);
		int pageSize = 12; // Set the number of items per page
        Page<Object[]> entityPage = attractionService.getAllPhoto(PageRequest.of(page-1, pageSize), id);
        model.addAttribute("entities", entityPage);
        model.addAttribute("ids", id);
        model.addAttribute("currentPage", page);
        model.addAttribute("place", result);
        
		return "GalleryPhoto";
	}
	
	@RequestMapping("/user/test2")
	public String calculateTimeAgodd(LocalDateTime insertTime) {
		return "PendingApproval";
	}
	
	@RequestMapping("/user/test")
	public String calculateTimeAgo(LocalDateTime insertTime) {
	    LocalDateTime currentTime = LocalDateTime.now();

	    Duration duration = Duration.between(insertTime, currentTime);

	    long secondsAgo = duration.getSeconds();
	    if (secondsAgo < 60) {
	        return secondsAgo + "s ago";
	    }
	    
	    long minutesAgo = duration.toMinutes();
	    if (minutesAgo < 60) {
	        return minutesAgo + "m ago";
	    }
	    
	    long hoursAgo = duration.toHours();
	    if (hoursAgo < 24) {
	        return hoursAgo + "h ago";
	    }
	    
	    long daysAgo = duration.toDays();
	    if (daysAgo < 24) {
	        return daysAgo + "d ago";
	    }
	    // Add logic for other time units like hours, days, etc., as needed

	    return "";
	}
	
}
