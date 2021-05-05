package ro.sd.a2.mapper;

import ro.sd.a2.dto.VoucherDto;
import ro.sd.a2.entity.Voucher;

public class VoucherMapper {

    public static VoucherDto entityToDto(Voucher voucher){
        VoucherDto voucherDto = new VoucherDto();
        voucherDto.setId(voucher.getId());
        voucherDto.setCategory(voucher.getCategory());
        voucherDto.setExpirationDate(voucher.getExpirationDate());
        voucherDto.setOrderDto(OrderMapper.entityToDto(voucher.getOrder()));
        voucherDto.setUserDto(UserMapper.entityToDto(voucher.getUser()));
        voucherDto.setValue(voucher.getValue());
        return voucherDto;
    }

    public static Voucher dtoToEntity(VoucherDto voucherDto){
        Voucher voucher = new Voucher();
        voucher.setId(voucherDto.getId());
        voucher.setCategory(voucherDto.getCategory());
        voucher.setExpirationDate(voucherDto.getExpirationDate());
        voucher.setOrder(OrderMapper.dtoToEntity(voucherDto.getOrderDto()));
        voucher.setUser(UserMapper.dtoToEntity(voucherDto.getUserDto()));
        voucher.setValue(voucherDto.getValue());
        return voucher;
    }
}
