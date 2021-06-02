package ec.fin.emagic.administrator.config;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Primary
@Slf4j
public class LoggerService {

	/**
	 * 
	 * @param action - Metodo o API que despliega en el log
	 * @param msgUser-  Mensaje que se muestra al usuario 
	 * @param exceptionMsg - excepción en caso de error
	 * @param codeStatus - código de HTTP de respuesta
	 * @return Mensaje estadar para el log
	 */
	public void build(String action, String msgUser, String exceptionMsg, int codeStatus )
	{		
		 log.info(String.format("[%s]\nmessage-user: %s\nexception:%s \ncode-status: %d", action, msgUser, exceptionMsg, codeStatus));
	}
	
	/**
	 * 
	 * @param action - Metodo o API que despliega en el log
	 * @param msgUser-  Mensaje que se muestra al usuario 
	 * @param exceptionMsg - excepción en caso de error
	 * @param codeStatus - código de HTTP de respuesta
	 * @return Mensaje estadar para el log
	 */
	public void msgInfo(String action, String msgUser, String exceptionMsg, int codeStatus )
	{		
		 log.info(String.format("[%s]\nmessage-user: %s\nexception:%s \ncode-status: %d", action, msgUser, exceptionMsg, codeStatus));
	}
	
	/**
	 * 
	 * @param action - Metodo o API que despliega en el log
	 * @param msgUser-  Mensaje que se muestra al usuario 
	 * @param exceptionMsg - excepción en caso de error
	 * @param codeStatus - código de HTTP de respuesta
	 * @return Mensaje estadar para el log
	 */
	public void msgWarn(String action, String msgUser, String exceptionMsg, int codeStatus )
	{		
		 log.warn(String.format("[%s]\nmessage-user: %s\nexception:%s \ncode-status: %d", action, msgUser, exceptionMsg, codeStatus));
	}
	
	/**
	 * 
	 * @param action - Metodo o API que despliega en el log
	 * @param msgUser-  Mensaje que se muestra al usuario 
	 * @param exceptionMsg - excepción en caso de error
	 * @param codeStatus - código de HTTP de respuesta
	 * @return Mensaje estadar para el log
	 */
	public void buildError(String action, String msgUser, String exceptionMsg, int codeStatus )
	{		
		log.error(String.format("[%s]\nmessage-user: %s\nexception:%s \ncode-status: %d", action, msgUser, exceptionMsg, codeStatus));
	}
	
	/**
	 * 
	 * @param action - Metodo o API que despliega en el log
	 * @param msgUser-  Mensaje que se muestra al usuario 
	 * @param exceptionMsg - excepción en caso de error
	 * @param codeStatus - código de HTTP de respuesta
	 * @return Mensaje estadar para el log
	 */
	public void buildDebug(String action, String msgUser, String exceptionMsg, int codeStatus )
	{		
		 log.debug(String.format("[%s]\nmessage-user: %s\nexception:%s \ncode-status: %d", action, msgUser, exceptionMsg, codeStatus));
	}
	
	
	
}
