package com.test.util;

public class MatrixUtil
{

    public static int multiplyMatricesCell(int[][] firstMatrix, int[][] secondMatrix, int row, int col)
    {
        int cell = 0;
        for (int i = 0; i < secondMatrix.length; i++)
        {
            cell ^= firstMatrix[row][i] * secondMatrix[i][col];
        }
        return cell;
    }
}
