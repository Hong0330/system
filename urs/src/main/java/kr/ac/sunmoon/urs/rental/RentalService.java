package kr.ac.sunmoon.urs.rental;

import java.util.List;

import kr.ac.sunmoon.urs.common.Message;
import kr.ac.sunmoon.urs.lockdevice.LockDevice;

public interface RentalService {
	public Message addRental(Rental rental) throws Exception;
	public Message returnUmbrella(Rental rental) throws Exception;
	public List<Rental> listRental(Rental rental) throws Exception;
	public Rental viewRental(Rental rental) throws Exception;
	public void editRental(Rental rental) throws Exception;
	public boolean deleteRental(Rental rental) throws Exception;
}
