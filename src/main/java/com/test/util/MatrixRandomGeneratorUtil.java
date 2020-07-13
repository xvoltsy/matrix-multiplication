package com.test.util;

import com.test.object.Matrix;

public class MatrixRandomGeneratorUtil
{

    public static Matrix generate(int height, int width)
    {
        int[][] matrix = new int[height][width];
        for (int i = 0; i < matrix.length; i++)
        {
            for (int j = 0; j < matrix[i].length; j++)
            {
                matrix[i][j] = Math.random() < 0.5 ? 0 : 1;
            }
        }
        return new Matrix(matrix);
    }
}
