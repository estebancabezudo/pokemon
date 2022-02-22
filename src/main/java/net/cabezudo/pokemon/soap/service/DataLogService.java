package net.cabezudo.pokemon.soap.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.cabezudo.pokemon.entities.DataLog;
import net.cabezudo.pokemon.repositories.DataLogRepository;

@Service
public class DataLogService {

	@Autowired
	DataLogRepository dataLogRepository;

	public DataLog log(HttpServletRequest request, String method) {
		String ip = request.getRemoteAddr();
		DataLog dataLog = new DataLog(ip, method);
		return dataLogRepository.save(dataLog);
	}

}
