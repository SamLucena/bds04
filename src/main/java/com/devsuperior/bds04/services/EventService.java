package com.devsuperior.bds04.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.bds04.dto.EventDTO;
import com.devsuperior.bds04.entities.City;
import com.devsuperior.bds04.entities.Event;
import com.devsuperior.bds04.repositories.EventRepository;

@Service
public class EventService{
	
	@Autowired
	private EventRepository repository;
	
	@Transactional
	public EventDTO insert(EventDTO dto) {
		Event event = new Event();
		event.setCity(new City(dto.getCityId(), null));
		event.setName(dto.getName());
		event.setDate(dto.getDate());
		event.setUrl(dto.getUrl());
		event = repository.save(event);
		return new EventDTO(event);
	}
	
	@Transactional
	public Page<EventDTO> findAll(Pageable pageable){
		Page<Event> page = repository.findAll(pageable);
		return page.map(c -> new EventDTO(c));
	}

}