package by.avectis.contracts.converter;

import by.avectis.contracts.model.Subdivision;
import by.avectis.contracts.service.subdivision.SubdivisionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


@Component
public class SubdivisionToWorkerConverter implements Converter<Object, Subdivision> {

	private static final Logger logger = LoggerFactory.getLogger(SubdivisionToWorkerConverter.class);
	
	@Autowired
	private
	SubdivisionService subdivisionService;

	public Subdivision convert(Object element) {
		Long id = Long.parseLong((String)element);
		Subdivision subdivision= subdivisionService.findById(id);
		logger.info("Subdivision : {}",subdivision);
		return subdivision;
	}
}