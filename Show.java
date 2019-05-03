package bst_ass;

import java.io.IOException;
import java.util.Scanner;

public class Show 
{
    public static void main(String[] args) throws IOException 
    {
        Bst_ass object = new Bst_ass();
	String aya = "indexFile.bin";
        int n;
        Scanner input = new Scanner(System.in);
        System.out.println("please enter records number:");
        n = input.nextInt();
        object.CreateRecordsFile(aya,n);
        int choice;
        while(true){
        System.out.println("please enter your choice:");
        System.out.println("1- InsertNewRecordAtIndex.");
        System.out.println("2- SearchRecordInIndex.");
        System.out.println("3- TraverseBinarySearchTreeInOrder.");
        System.out.println("4- DisplayIndexFileContent.");
        System.out.println("5- exit.");
        choice = input.nextInt();
        switch (choice)
        {
            case 1:
            {
                int Key,ByteOffset;
                System.out.println("please enter key:");
                Key= input.nextInt();
                System.out.println("please enter offset:");
                ByteOffset= input.nextInt();
                object.InsertNewRecordAtIndex(aya, Key , ByteOffset);
               break;
            }
           case 2:
            {   int Key;
                System.out.println("please enter key u search about:");
                Key= input.nextInt();
                if(object.SearchRecordInIndex(aya, Key)!=-1)
                {
                	System.out.println("Offset of searched key Is=>>"+object.SearchRecordInIndex(aya, Key));
                }
                else 
                {
                	System.out.println("NOT FOUND");
                }
                
                break;
            }
            case 3:
            {  
               int s=1;
               object.TraverseBinarySearchTreeInOrder (aya,s);
            }
            case 4:
            {
                System.out.println("*********************Display***********************");		
		object.DisplayIndexFileContent(aya);
                break;
            }
            case 5:
                break;
        
        }  
    }
    }
}
