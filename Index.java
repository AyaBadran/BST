package bst_ass;

public class Index 
{
    public int value;
    public int offset;
    public int left;
    public int right;
    
    public Index() {}

    public Index(int v, int off, int left, int right)
    {
        value = v;
        offset = off;
        this.left = left;
        this.right = right;
    }
    public Index(int k)
    {
        value = k;
        offset = 0;
        left = 0;
        right = 0; 
    }
    public void setvalue(int V)
    {
        value=V;
    }
    public void setoffset(int F)
    {
        offset=F;
    }
    public void setright(int R)
    {
        right=R;
    }
    public void setleft(int L)
    {
        left=L;
    }
    public int getvalue()
    {
        return value;
    }
    public int getoffset()
    {
        return offset;
    }
    public int getright()
    {
        return right;
    }
    public int getleft()
    {
       return left;
    }
    
}

