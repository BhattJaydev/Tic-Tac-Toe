public class stack
{
	static final int MAX = 1000;
	int top;
	int a[] = new int[MAX]; 

	boolean isEmpty()
	{
		return (top < 0);
	}
	stack()
	{
		top = -1;
	}

	boolean push(int x){
		if (top >= (MAX - 1)) 
		{
			return false;
		}
		else 
		{
			a[++top] = x;
			return true;
		}
	}

	int pop() {
		if (top < 0) 
		{
			return -1;
		}
		else 
		{
			return a[top--];
		}
	}

	int peek() throws Exception {
		if (top < 0) 
		{
			throw new Exception("Stack Underflow");
		}
		else 
		{
			return a[top];
		}
	}
		
}