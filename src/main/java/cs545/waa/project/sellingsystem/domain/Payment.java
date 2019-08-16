package cs545.waa.project.sellingsystem.domain;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Payment {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "payment_id")
    private Integer id;
	
	@DateTimeFormat(pattern = "MM-dd-yyyy")
	private LocalDate paymentDate;
	

}
