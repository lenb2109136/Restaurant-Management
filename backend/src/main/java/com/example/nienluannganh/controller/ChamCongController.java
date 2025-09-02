package com.example.nienluannganh.controller;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.nienluannganh.DTO.caSoLuong;
import com.example.nienluannganh.model.Ca;
import com.example.nienluannganh.model.ThoiGianLamCa;
import com.example.nienluannganh.objectcontroller.responseentity.response;import com.example.nienluannganh.repository.CaRepository;
import com.example.nienluannganh.repository.ThoiGianLamCaRepository;
import jakarta.transaction.Transactional;

@RestController

public class ChamCongController {
    @Autowired
    ThoiGianLamCaRepository thoiGianCaLamRepository;
    @Autowired
    CaRepository caRepository;
    
    
    public Ca timCaPhuHop(List<Ca> danhSachCa, LocalTime thoiGianHienTai) {
        Ca caPhuHop = null;
        long d = 555555555555555L; // Giới hạn thời gian (12h đêm)
        LocalTime dem12h = LocalTime.of(23, 59, 59);  
        for (int i = 0; i < danhSachCa.size(); i++) {
            LocalTime thoiGianBatDau = danhSachCa.get(i).getThoiGianBatDau();
            LocalTime thoiGianKetThuc = danhSachCa.get(i).getThoiGianKetThuc();

            // Kiểm tra thời gian hiện tại không nằm trong khoảng của ca
            boolean isTrongKhoangThoiGian = false;
            if (thoiGianBatDau.isBefore(thoiGianKetThuc)) {
                isTrongKhoangThoiGian = !thoiGianHienTai.isBefore(thoiGianBatDau) && thoiGianHienTai.isBefore(thoiGianKetThuc);
            } else {
                // Nếu ca qua đêm (vượt qua 12h đêm)
                isTrongKhoangThoiGian = !(thoiGianHienTai.isBefore(thoiGianBatDau) && thoiGianHienTai.isAfter(thoiGianKetThuc));
            }

            // Kiểm tra các điều kiện còn lại
            if (!isTrongKhoangThoiGian && 
                thoiGianHienTai.isBefore(thoiGianBatDau) && 
                Duration.between(thoiGianHienTai, thoiGianBatDau).toMinutes() < d && // Thời gian gần nhất
                thoiGianBatDau.isBefore(dem12h)) { // Thời gian bắt đầu không vượt quá 12 giờ đêm
                caPhuHop = danhSachCa.get(i);
            }
        }

        return caPhuHop;
    }




// toàn bộ danh sách phân công trong thười điểm hiênj tại
    @GetMapping("/chamcong/getall")
    public ResponseEntity<Object> caHomNay(@RequestParam(name = "date", required = false) LocalDate date,
            @RequestParam(name = "caId", required = false) Integer caId) {
        List<ThoiGianLamCa> thoiGianCaLam = thoiGianCaLamRepository.getAllThoiGianLamCaNow();
        response r = new response(HttpStatus.OK, "success", thoiGianCaLam);
        return new ResponseEntity<>(r, HttpStatus.OK);
    }

    ///đã chấm công dựa trên ngày chấm công dựa trên ngày ca
    @GetMapping("chamcong/dachamcong")
    public ResponseEntity<Object> thoiGianLamCa(@RequestParam(name = "date", required = false) LocalDate date,
            @RequestParam(name = "caId", required = false) Integer caId) {
        if (caId == null || date == null) {
            return new ResponseEntity<>(
                    new response(HttpStatus.BAD_REQUEST, "Vui lòng chọn đầy đủ ngày và ca làm việc", caId),
                    HttpStatus.BAD_REQUEST);
        }
        List<ThoiGianLamCa> thoiGianLamCas = thoiGianCaLamRepository.getPhanCongDaChamCong(date, caId);
        return new ResponseEntity<>(new response(HttpStatus.OK, "success", thoiGianLamCas), HttpStatus.OK);
    }
    ///chưa chấm công dựa trên ngày chấm công dựa trên ngày ca
    @GetMapping("chamcong/chuachamcong")
    public ResponseEntity<Object> chuaChamCong(@RequestParam(name = "date", required = false) LocalDate date,
            @RequestParam(name = "caId", required = false) Integer caId) {
        if (caId == null || date == null) {
            return new ResponseEntity<>(
                    new response(HttpStatus.BAD_REQUEST, "Vui lòng chọn đầy đủ ngày và ca làm việc", caId),
                    HttpStatus.BAD_REQUEST);
        }
        List<ThoiGianLamCa> thoiGianLamCas = thoiGianCaLamRepository.getPhanCongChuaChamCong(date, caId);
        return new ResponseEntity<>(new response(HttpStatus.OK, "success", thoiGianLamCas), HttpStatus.OK);
    }

    // chấm công bắt đầu
    @Transactional
    @PostMapping("/chamcong")
    public ResponseEntity<Object> chamCong(
            @RequestParam(name = "userId", defaultValue = "-1", required = false) Integer userId) {
        if (userId != null && userId != -1) {
            ThoiGianLamCa t = thoiGianCaLamRepository.getPhanCongByUserIdAndNow(userId).orElse(null);
            Ca cc= timCaPhuHop(caRepository.findAll(),LocalTime.now());
            System.out.println(cc.getId());
            ThoiGianLamCa tt=thoiGianCaLamRepository.huhu(userId, cc.getId(), LocalDate.now());
            System.out.println(tt);
           System.out.println("thời gian làm ca ");
           System.out.println(t!=null);
           System.out.println(tt!=null);
            if(t==null&&tt!=null) {
            	 tt.setBatDau(LocalTime.now());
                 thoiGianCaLamRepository.save(tt);
            	return new ResponseEntity<>(
                        new response(HttpStatus.BAD_REQUEST, "Bạn đi sớm cho ca làm: "+tt.getCa().getThoiGianBatDau()+" - "+tt.getCa().getThoiGianKetThuc(),null),
                        HttpStatus.OK);
            }
            if (t != null) {
                t.setBatDau(LocalTime.now());
                thoiGianCaLamRepository.save(t);
                return new ResponseEntity<>(
                        new response(HttpStatus.BAD_REQUEST, "Bạn đi sớm cho ca làm: "+tt.getCa().getThoiGianBatDau()+" - "+tt.getCa().getThoiGianKetThuc(), null),
                        HttpStatus.OK);
            }
            return new ResponseEntity<>(
                    new response(HttpStatus.BAD_REQUEST, "Nhân viên không có ca làm khung giờ này", null),
                    HttpStatus.OK);
        }
        return new ResponseEntity<>(new response(HttpStatus.BAD_REQUEST, "Nhân viên không hợp lệ", null),
                HttpStatus.BAD_REQUEST);
    }

    // chấm công kết thúc
    @Transactional
    @PostMapping("/chamcong/end")
    public ResponseEntity<Object> chamCongEnd(
            @RequestParam(name = "userId", defaultValue = "-1", required = false) Integer userId,
            @RequestParam(name = "caId", defaultValue = "-1", required = false) Integer caId) {
        if (userId != null && userId != -1&&caId!=null) {
            ThoiGianLamCa t = thoiGianCaLamRepository.getEndPhanCongByUserIdAndNow(userId).orElse(null);
           if(t!=null&&t.getBatDau()==null) {
        	   return new ResponseEntity<>(
                       new response(HttpStatus.BAD_REQUEST, "Bạn chưa thực hiện chấm công vào, vui lòng chấm công vào trước", null),
                       HttpStatus.BAD_REQUEST);
           }
            if (t != null) {
                t.setKetThuc(LocalTime.now());
                thoiGianCaLamRepository.save(t);
                return new ResponseEntity<>(
                        new response(HttpStatus.OK, "Chấm công thành công ", null),
                        HttpStatus.OK);
            }
            return new ResponseEntity<>(
                    new response(HttpStatus.BAD_REQUEST, "Không tìm thấy phân công đang làm cho nhân viên này.", null),
                    HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new response(HttpStatus.BAD_REQUEST, "User id không hợp lệ", null),
                HttpStatus.BAD_REQUEST);
    }
    ///chưa chấm công chấm công dựa trên ngày chấm công dựa trên ngày ca
    @GetMapping("/getphancong/user")
    public ResponseEntity<Object> getPhanCongNowByUserId(@RequestParam(name = "userId") Integer userId) {
        if (userId != null) {
            ThoiGianLamCa t = thoiGianCaLamRepository.getPhanCongByUserIdAndNow(userId).orElse(null);
            return new ResponseEntity<>(
                    new response(HttpStatus.OK, "Không tìm thaays thông tin phân công cho user này.", t),
                    HttpStatus.OK);
        }
        return new ResponseEntity<>(
                new response(HttpStatus.BAD_REQUEST, "Không tìm thaays thông tin phân công cho user này.", userId),
                HttpStatus.BAD_REQUEST);
    }

    // đã vô làm rồi nhưung chưa điểm danh kết thúc
//    @GetMapping("/getphancong/end/user")
//    public ResponseEntity<Object> getEndPhanCongNowByUserId(@RequestParam(name = "userId") Integer userId) {
//        if (userId != null) {
//            ThoiGianLamCa t = thoiGianCaLamRepository.getEndPhanCongByUserIdAndNow(userId).orElse(null);
//            return new ResponseEntity<>(
//                    new response(HttpStatus.OK, "Không tìm thấy thông tin kết thúc phân công cho user này.", t),
//                    HttpStatus.OK);
//        }
//        return new ResponseEntity<>(
//                new response(HttpStatus.BAD_REQUEST, "Không tìm thaays thông tin phân công cho user này.", userId),
//                HttpStatus.BAD_REQUEST);
//    }

}
