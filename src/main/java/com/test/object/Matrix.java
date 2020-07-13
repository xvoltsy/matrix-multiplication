package com.test.object;

public class Matrix
{
    private int[][] array;

    public Matrix(int[][] array)
    {
        this.array = array;
    }

    public Matrix(int dimension)
    {
        this.array = new int[dimension][dimension];
    }

    public Matrix(int height, int width)
    {
        this.array = new int[height][width];
    }

    public int[][] getArray()
    {
        return array;
    }
}
