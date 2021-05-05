package ro.sd.a2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.sd.a2.dto.VoucherDto;
import ro.sd.a2.entity.Order;
import ro.sd.a2.entity.User;
import ro.sd.a2.entity.Voucher;
import ro.sd.a2.mapper.VoucherMapper;
import ro.sd.a2.repository.VoucherRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class VoucherService {

    @Autowired
    private VoucherRepository voucherRepository;

    public void addVoucher(Voucher voucher){
        voucherRepository.save(voucher);
    }

    public List<VoucherDto> getVouchersForUser(User user){
        List<Voucher> vouchers = voucherRepository.findByUser(user);
        List<VoucherDto> voucherDtos = new ArrayList<>();
        for (Voucher voucher: vouchers) {
            voucherDtos.add(VoucherMapper.entityToDto(voucher));
        }
        return voucherDtos;
    }

    public List<VoucherDto> getVouchersForOrder(Order order){
        List<Voucher> vouchers = voucherRepository.findByOrder(order);
        List<VoucherDto> voucherDtos = new ArrayList<>();
        for (Voucher voucher: vouchers) {
            voucherDtos.add(VoucherMapper.entityToDto(voucher));
        }
        return voucherDtos;
    }
}
