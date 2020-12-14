package kr.ac.sunmoon.urs.rental;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RentalMapper {
    public int count(Rental rental) throws Exception;
    public List<Rental> list(Rental rental) throws Exception;
    public Rental select(Rental rental) throws Exception;
    public Rental selectByLastOne(Rental rental) throws Exception;
    public void insert(Rental rental) throws Exception;
    public void update(Rental rental) throws Exception;
    public void delete(Rental rental) throws Exception;
}