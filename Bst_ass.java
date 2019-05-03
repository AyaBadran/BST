package bst_ass;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Bst_ass 
{
    public void writeInFile(RandomAccessFile file,Index in, int offs) throws FileNotFoundException, IOException
    {   try {
        file.seek(offs);
        file.writeInt(in.value);
        file.writeInt(in.offset);
        file.writeInt(in.left);
        file.writeInt(in.right); 
        } catch (IOException e) {}
    }
    
    // Create Records File
    void CreateRecordsFile(String filename, int numberOfRecords)
    {
        RandomAccessFile file;
        try {
                file = new RandomAccessFile(filename, "rw");
                Index obj; //make object with current node
                for (int i = 0; i < numberOfRecords; i++)
               {
                    if (i == numberOfRecords - 1)
                    {
                            obj = new Index(-1);//here we call parametarized construcror and pass key to it
                    }
                    else 
                    {
                            obj = new Index(i + 1);
                    }
                    writeInFile(file, obj, i * 16);
                }
                file.close();
        } catch (IOException e) {}

    }
    // Insert New Record
    int InsertNewRecordAtIndex(String filename, int Key, int ByteOffset) throws IOException 
    {
        // Read First Index to get first Empty Place
        RandomAccessFile file = new RandomAccessFile(filename, "rw");
        file.seek(0);
        int free = file.readInt();
        if (free == -1) 
        {   // y3nii key became -1 we cant insert any more
                return free;
        }
        // Seek to the empty place 
        int Go = free * 16;
        file.seek(Go);
        // Get the first value in the empty space the nodevalue 
        int nodeValue = file.readInt();
        // Write Node in The Empty Place
        Index obj = new Index(Key, ByteOffset, -1, -1);
        writeInFile(file, obj, Go);
        // If current node is the First Node to be added
        if (free == 1) 
        {
            file.seek(0);
            file.writeInt(2);
            return free;
        }
       // If there are other nodes, we need to add current node to left of right
        int currentIndex = 1;
        while (true)
        {
            file.seek(currentIndex * 16);
            Index objj = new Index(file.readInt(), file.readInt(), file.readInt(), file.readInt());
            if (Key >= objj.value) 
            {
                if (objj.right == -1) 
                {
                    objj.right = free;
                    writeInFile(file, objj, currentIndex * 16);
                    break;
                }
                else
                    currentIndex = objj.right;
            }
            else 
            {
                if (objj.left == -1) 
                {
                    objj.left = free;
                    writeInFile(file, objj, currentIndex * 16);
                    break;
                }
                else
                    currentIndex = objj.left;
            }
        }

        // Added Index of the new empty place in the first Index
        if (nodeValue == -1) 
        {
            file.seek(0);
            file.writeInt(-1);
        } 
        else 
        {
            file.seek(0);
            file.writeInt(free + 1);
        }
        return free;
    }
    	// Display Index File Content
    void DisplayIndexFileContent(String filename) throws IOException 
    {
        RandomAccessFile indexFile = new RandomAccessFile(filename, "rw");
        int numberOfRecords = (int) (indexFile.length() / 16);
        System.out.println("Key \t Offset \t Left \t Right");
        for (int i = 0; i < numberOfRecords; i++)
        {
                System.out.println(indexFile.readInt() + " 	 " + indexFile.readInt() + " 	   \t" 
                        + indexFile.readInt() + " 	 "+ indexFile.readInt());
        }
    }
    // Search In Tree
    
// Search In Tree
    int SearchRecordInIndex(String filename, int Key) throws IOException 
    {
		
        RandomAccessFile file = new RandomAccessFile(filename, "rw");
        int currentIndex = 1;
        while (true)
        {
            file.seek(currentIndex * 16);
            Index Ind = new Index(file.readInt(),file.readInt(),file.readInt(),file.readInt());
            if (Ind.value == Key)
                    return Ind.offset;
            else if (Key > Ind.value)
            {
                if (Ind.right == -1 || Ind.right == 0) 
                {
                        return -1;
                }
                else
                {
                        currentIndex = Ind.right;
                }
            }
            else if (Key < Ind.value) 
            {
                if (Ind.left == -1 || Ind.left == 0) 
                {
                        return -1;
                }
                else
                {
                        currentIndex = Ind.left;
                }
            }
        }
    }
    	// Display Tree Nodes in Order
    void TraverseBinarySearchTreeInOrder(String FileName, int seekPosition) throws IOException 
    {
        // Read Current Node
        RandomAccessFile file = new RandomAccessFile(FileName, "rw");
        file.seek(seekPosition * 16);
        Index currentNode = new Index(file.readInt(), file.readInt(), file.readInt(), file.readInt());

        // Traverse Left If Exists
        if (currentNode.left != -1)
        {
                TraverseBinarySearchTreeInOrder(FileName, currentNode.left);
        }
        System.out.println(currentNode.value);		
        // Traverse Right If Exists
        if (currentNode.right != -1)
        {
                TraverseBinarySearchTreeInOrder(FileName, currentNode.right);
        }
        // Out when there is no left or right
        if (currentNode.left == -1 && currentNode.right == -1)
                return;
    }
}