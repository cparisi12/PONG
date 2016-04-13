//AI alarm that carries out tasks located
//in the performAITask method by overriding
//the takeNotice method in the alarm class

public class AIAlarm extends Alarm {
	
	private AIAlarmListener whoWantsToKnow;

	public AIAlarm(){
		super();
		whoWantsToKnow = null;
	}
	
	public AIAlarm(AIAlarmListener somebody){
		super(somebody);
		whoWantsToKnow = somebody;
	}
	
	@Override
	public void takeNotice() {
		if (whoWantsToKnow!=null)
			whoWantsToKnow.performAITask();

	}

}
