
public class Stack<T> implements StackInterface<T>{
	public T[] stack;
	public static int itemsOnStack;
	
	public Stack (int initialSize) {
		stack = (T [ ]) new Object[initialSize];
		itemsOnStack = 0;
		}
	
	@Override
	public void push(T item) {
		// TODO Auto-generated method stub
		if(isFull())
		{
			expandArray();
		}
		
		if (isFull() == false)
		{
			stack[itemsOnStack] = item;
			++itemsOnStack;
		}
		else
		{
			System.out.println("Stack is full");
		}

	}

	@Override
	public T peek() {
		// TODO Auto-generated method stub
		if (isEmpty() == false)
		{
			return stack[itemsOnStack - 1];
		}
		else
		{
			return null;
		}
	}

	@Override
	public T pop() {
		// TODO Auto-generated method stub
		if (isEmpty() == false)
		{
			--itemsOnStack;
			return stack[itemsOnStack];
		}
		else
		{
			return null;
		}
	}

	@Override
	public boolean isFull() {
		// TODO Auto-generated method stub
		if (itemsOnStack == stack.length)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		if (itemsOnStack == 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	private void expandArray()
	{
		T[] biggerStack = (T [ ] ) new Object[stack.length + 10];
		
		for (int i = 0; i < stack.length; ++i)
		{
			biggerStack[i] = stack[i];
		}
		stack = biggerStack;
	}
}
