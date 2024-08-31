// Simulates ARRAYLIST, have students implement this
// to better understand how the java api works

public class MyArrayList
{
    private Object [] list;
    private int numElements;       // number of students currently in the
                                    // list
    // Constructs the list, initially empty,
    // but can hold up to 100
    public MyArrayList ()
    {
        list = new Object[100];
        numElements = 0;
    }

    // Adds t to the end of the list
    public void add (Object t)
    {
        if (numElements < list.length)
        {
            list[numElements] = t;
        }
            
        else
        {
            Object [] newList = new Object[list.length * 2];
            for (int i = 0; i < list.length; i++)
            {
                newList[i] = list[i]; //Add each element from old list 
                                      //to the new list
            }
            newList[numElements] = t;
            list = newList;
        }
        numElements++;
    }

    // Returns the number of active elements on the list
    public int size ()
    {
        return numElements;
    }

    // Returns the student in the i'th location (counting from 0)
    // Precondition: 0 <= i < size()
    public Object get (int i)
    {
        if (0 <= i && i < size())
        {
            return list[i];
        }
            
        else
        {
            throw new IndexOutOfBoundsException();
        }
    }

    // Sets the i'th location in the list to t;
    // returns the previous i'th element.
    public Object set (int i, Object t)
    {
        if(i >= 0 && i < numElements) 
        {
            Object temp = list[i];
            list[i] = t;
            return temp;
        }
            
        else 
        {
            throw new IndexOutOfBoundsException();
        }
    }
    // Removes the i'th element, sliding all items beyond i up by one spot.
    // Returns the element removed
    public Object remove (int i)
    {

        if(i >= 0 && i < numElements) 
        {
            Object temp = list[i];
            for(int x = i; x < numElements - 1; x++) 
            {
                list[x] = list[x + 1];
            }
            list[numElements - 1] = null;
            numElements--;
            return temp;
        }
        else 
        {
            throw new IndexOutOfBoundsException();
        }
    }
}
