import java.awt.Graphics;

/**
 * 
 *@author ChrisParisi
 *
 */

public class LinkedCollection implements DataCollection {

	//location variables
	private int x, y; 
	//first node in chain reference
	private Node topNode;  
	//selected node reference 
	private Node selected; 


	//default constructor
	public LinkedCollection(){

	}


	//method to add and select 
	//a received item
	@Override
	public void add(Ball someBall) {
		// TODO Auto-generated method stub

		//node references
		Node temp;
		Node temp2;

		//starts empty list
		if(topNode == null){
			
			temp = new Node(someBall, null);
			topNode = temp;

		}

		//adds to an existing list
		else{
			temp = topNode;
			temp2 = new Node(someBall, temp);
			topNode = temp2;
		}

		//sets the node to be 
		//the selected node
		selected = topNode;

		//location on the item
		

	}

	//removes given item if any
	//and the item is no longer selected
	@Override
	public void remove() {
		// TODO Auto-generated method stub

		Node remove;

		if(selected != null){

			Node temp = topNode;

			//since selected is being removed
			//if selected is topNode then
			//topNode will be set to the next one
			if(temp == selected){
				topNode = topNode.getNext();
			}
			else{
				//we find the selected if not topNode
				while(temp.getNext() != selected){
					temp = temp.getNext();
				}

				//remove takes the item reference
				remove = temp.getNext();
				//refresh list and shift 
				refresh(remove);
				temp.setNext(remove.getNext());
				

			}

			//selected is removed and now null
			selected = null;

		}



	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub

		selected = topNode;
	}

	//removes any highlighted items
	//then resets selected to the given 
	//item if the item is in the list
	@Override
	public void reset(Ball someBall) {
		// TODO Auto-generated method stub

		Node temp = topNode;


		//if no item is passed
		//selected is null
		if(someBall == null){
			selected = null;
		}

		else{

			temp = topNode;

			//finds selected item
			while(temp != null && ((Ball)(temp.getData())) != someBall){
				temp = temp.getNext();

			}

			//highlights selected
			if(temp != null){
				selected = temp;
				
			}
		}


	}

	//determines whether the selected
	//has a proceeding Item
	@Override
	public boolean hasNext(){
		// TODO Auto-generated method stub

		//there is no next if selected or
		//the following item are null
		if(selected == null){
			return false;
		}
		
		//otherwise there is a next item
		else{
			return true;
		}
		
	}

	//returns selected item and 
	//the following item is selected
	@Override
	public Ball next() {
		// TODO Auto-generated method stub

		if(selected != null){
			Object result = selected.getData();
			selected = selected.getNext();
			return (Ball) result;
		}
		else{

			return null;
		}
	}

	//paint method
	@Override
	public void paint(Graphics pane) {
		// TODO Auto-generated method stub

		Node temp = topNode;

		//loop through list painting each item
		while(temp!=null){
			((Ball)temp.getData()).paint(pane);
			temp = temp.getNext();			
		}

	}

	

	

	//recursive method to update 
	//references in linked collection
	public Node refresh(Node top){

		Node result = null;

		if(top==null){
			result = null;
		}
		else if(top.getNext() == null){
			result = null;
		}
		else{
			//recursive call
			result = refresh((top.getNext()));
			
		}

		return result;
	}

	//Inner Node class
	private class Node{

		//Variable declaration
		private Node next;
		private Object data;

		//default constructor
		public Node(){
			next = null;
			data = null;
		}

		public Node(Object dat, Node nex){
			next = nex;
			data = dat;
		}

		public Object getData(){
			return data;
		}

		public void setData(Object dat){
			data = dat;
		}

		public Node getNext(){
			return next;
		}

		public void setNext(Node nex){
			next = nex;
		}

	}

}
