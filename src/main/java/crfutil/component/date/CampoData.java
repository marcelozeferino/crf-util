package main.java.crfutil.component.date;

import java.util.Calendar;

/**
 * Enum contendo os tipos de campos (dia, m�s e ano) que podem 
 * ser utilizados em opera��es com datas no objeto IntegerDate.
 *  
 * @author Marcelo Zeferino
 *
 */
public enum CampoData {

		DIA{
			@Override
			int obterCalendarField() {
				return Calendar.DAY_OF_MONTH;
			}	
		},
		MES{
			@Override
			int obterCalendarField() {
				return Calendar.MONTH;
			}
		},
		ANO{
			@Override
			int obterCalendarField() {
				return Calendar.YEAR;
			}
		};

		abstract int obterCalendarField();
		
	};

