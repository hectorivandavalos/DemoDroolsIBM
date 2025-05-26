package mx.com.metlife.reservas.main.domain;

import java.math.BigDecimal;

import org.joda.time.LocalDate;

public class DetalleRetiroRva {
	
	private LocalDate fechaRva;
	private int tipoReserva;
	private int tipoFondo;
	private int idConcepto;
	private BigDecimal importeRva;
	
	
	public LocalDate getFechaRva() {
		return fechaRva;
	}
	public void setFechaRva(LocalDate fechaRva) {
		this.fechaRva = fechaRva;
	}
	public int getTipoReserva() {
		return tipoReserva;
	}
	public void setTipoReserva(int tipoReserva) {
		this.tipoReserva = tipoReserva;
	}
	public int getTipoFondo() {
		return tipoFondo;
	}
	public void setTipoFondo(int tipoFondo) {
		this.tipoFondo = tipoFondo;
	}
	public int getIdConcepto() {
		return idConcepto;
	}
	public void setIdConcepto(int idConcepto) {
		this.idConcepto = idConcepto;
	}
	public BigDecimal getImporteRva() {
		return importeRva;
	}
	public void setImporteRva(BigDecimal importeRva) {
		this.importeRva = importeRva;
	}

}
