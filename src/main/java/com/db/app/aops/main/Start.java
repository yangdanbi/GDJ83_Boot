package com.db.app.aops.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.db.app.aops.pays.Card;
import com.db.app.aops.transfers.Transfer;

@Component
public class Start {
	@Autowired
	private Transfer transfer;
	@Autowired
	private Card card;
	
	public void go() {
		//버스틀 타고 내려서 다시 지하철탐
		//card.cardCheck();
		transfer.takeBus(50);
		//card.cardCheck();
		
		//card.cardCheck();
		transfer.takeSubway(15L,"winter");
		//card.cardCheck();
		
		transfer.walk();
	}

}
