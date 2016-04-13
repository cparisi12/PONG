//game alarm that carries out tasks located
//in the takeNotice method

public class GameAlarm extends Alarm {
	private GameAlarmListener whoWantsToKnow;
	
	public GameAlarm(){
		super();
		whoWantsToKnow = null;
	}
	
	public GameAlarm(GameAlarmListener somebody){
		super(somebody);
		whoWantsToKnow = somebody;
	}

	public void takeNotice() {
		if (whoWantsToKnow!=null)
			whoWantsToKnow.takeNotice();
	}

}
