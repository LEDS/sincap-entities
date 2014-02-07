package br.ifes.leds.sincap.controleInterno.cln.cdp;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

/**
 * @author 20121BSI0252
 *
 */
@Entity
public class InstituicaoNotificadora extends Instituicao{
	@OneToOne
	private BancoOlhos bancoOlhos;

	public BancoOlhos getBancoOlhos() {
		return bancoOlhos;
	}

	public void setBancoOlhos(BancoOlhos bancoOlhos) {
		this.bancoOlhos = bancoOlhos;
	}
	
	
}
