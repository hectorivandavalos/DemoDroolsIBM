package mx.com.metlife.reservas.calc.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.joda.time.LocalDate;


public class SaldosReserva implements Serializable{
	
	private static final long serialVersionUID = 2959942203044541467L;

	private LocalDate fecha;
	
	private BigDecimal saldoInicialF1 = BigDecimal.ZERO;
	
	private BigDecimal saldoInicialF2 = BigDecimal.ZERO;
	
	private BigDecimal saldoInicialFE = BigDecimal.ZERO;
	
	private int tipo;
	
	private boolean existeConceptoSaldoInicialF1;
	
	private boolean existeConceptoSaldoInicialF2;
	
	private boolean existeConceptoSaldoInicialFE;

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public BigDecimal getSaldoInicialF1() {
		if(this.saldoInicialF1 == null){
			return BigDecimal.ZERO;
		}
		return saldoInicialF1;
	}

	public void setSaldoInicialF1(BigDecimal saldoInicialF1) {
		if(saldoInicialF1 != null) {
			this.saldoInicialF1 = saldoInicialF1;
			this.existeConceptoSaldoInicialF1 = true;
		}
		
	}

	public BigDecimal getSaldoInicialF2() {
		if(this.saldoInicialF2 == null){
			return BigDecimal.ZERO;
		}
		return saldoInicialF2;
	}

	public void setSaldoInicialF2(BigDecimal saldoInicialF2) {
		if(saldoInicialF2 != null) {
			this.saldoInicialF2 = saldoInicialF2;
			this.existeConceptoSaldoInicialF2 = true;
		}
	}

	public BigDecimal getSaldoInicialFE() {
		if(this.saldoInicialFE == null){
			return BigDecimal.ZERO;
		}
		return saldoInicialFE;
	}

	public void setSaldoInicialFE(BigDecimal saldoInicialFE) {
		if(saldoInicialFE != null) {
			this.saldoInicialFE = saldoInicialFE;	
			this.existeConceptoSaldoInicialFE = true;
		}
		
	}

	/**
	 * @return the tipo
	 */
	public int getTipo() {
		return tipo;
	}

	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	
	public BigDecimal getSaldoInicialF1AjusteLinea() {
		return saldoInicialF1;
	}
	
	public BigDecimal getSaldoInicialF2AjusteLinea() {
		return saldoInicialF2;
	}
	
	public BigDecimal getSaldoInicialFEAjusteLinea() {
		return saldoInicialFE;
	}
	
	public boolean isExisteConceptoSaldoInicialF1() {
		return existeConceptoSaldoInicialF1;
	}

	public void setExisteConceptoSaldoInicialF1(boolean existeConceptoSaldoInicialF1) {
		this.existeConceptoSaldoInicialF1 = existeConceptoSaldoInicialF1;
	}

	public boolean isExisteConceptoSaldoInicialF2() {
		return existeConceptoSaldoInicialF2;
	}

	public void setExisteConceptoSaldoInicialF2(boolean existeConceptoSaldoInicialF2) {
		this.existeConceptoSaldoInicialF2 = existeConceptoSaldoInicialF2;
	}

	public boolean isExisteConceptoSaldoInicialFE() {
		return existeConceptoSaldoInicialFE;
	}

	public void setExisteConceptoSaldoInicialFE(boolean existeConceptoSaldoInicialFE) {
		this.existeConceptoSaldoInicialFE = existeConceptoSaldoInicialFE;
	}
	
	public boolean isZero() {
		return this.getSaldoInicialF1().compareTo(BigDecimal.ZERO) == 0  && this.getSaldoInicialF2().compareTo(BigDecimal.ZERO) == 0 && this.getSaldoInicialFE().compareTo(BigDecimal.ZERO) == 0;
	}

	
}
