package com.neko.game.duel.period;

public class Turn_Before_Start extends Period{
	public int status;
	
	public Turn_Before_Start(int status){
		this.status = status;		
	}
	
	public void act(){
		System.out.println("-----act--------");
	};

}
