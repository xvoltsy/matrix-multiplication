package com.test;

import com.test.multipliers.Multiplier;
import com.test.multipliers.matrix.MatrixParallelMultiplier;
import com.test.multipliers.matrix.MatrixSequentialMultiplier;
import com.test.object.Matrix;

import com.test.util.MatrixRandomGeneratorUtil;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Date;

public class MatrixMultiplicationEntry
{

    public static void main(String[] args)
    {
        System.out.println("Enter matrix length: ");

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Integer length;
        do
        {
            try
            {
                length = Integer.parseInt(reader.readLine());
                if ( length < 1 || length > 10000 )
                {
                    System.out.println("Matrix length should be in range [1..10000]");
                    length = null;
                }
            }
            catch ( Exception e )
            {
                System.out.println("You entered incorrect data!");
                length = null;
            }
        }
        while (length == null);
        System.out.println("Processing..");

        Matrix firstMatrix = MatrixRandomGeneratorUtil.generate(length, length);
        Matrix secondMatrix = MatrixRandomGeneratorUtil.generate(length, length);

        Date start = new Date();

        Multiplier<Matrix> multiplier = new MatrixSequentialMultiplier();
        multiplier.multiply(firstMatrix, secondMatrix);

        Date end = new Date();
        System.out.println("\nTime taken in milli seconds for sequential multiplication: " + (end.getTime() - start.getTime()));

        start = new Date();

        multiplier = new MatrixParallelMultiplier();
        multiplier.multiply(firstMatrix, secondMatrix);

        end = new Date();
        System.out.println("\nTime taken in milli seconds for parallel multiplication: " + (end.getTime() - start.getTime()));
    }

}
