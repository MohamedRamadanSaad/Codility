package codelity;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

public class CyclicRotation {

	public static void main(String... args) {
		int A[] = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		int B[] = new int[] { 1, 2, 3, 4, 5 };
		int C[] = new int[] { 3, 8, 9, 7, 6 };
		int D[] = new int[] { 3, 2, 4 };
		int E[] = new int[] {}; // empty array
		int G[] = new int[] { -5, -6, -1, -2, -3, -4, -5, -6, -1 };

		CyclicRotation so = new CyclicRotation();
		int K = 5;
//-------------------max
		Random rd = new Random(); // creating Random object
		int[] maxArray = new int[100];
		for (int i = 0; i < maxArray.length; i++) {
			maxArray[i] = rd.nextInt(); // storing random integers in an array
//			System.out.println(arr[i]); // printing each array element
		}
		// -----------

		int result[] = so.solution(D, K);
		for (int i : result) {
			System.out.print(i + " ");
		}
	}

	public int[] solution(int[] A, int K) {
		if (A.length == 0 || A.length == 1)
			return A;

		if (K <= 0)
			return A;

		if (A.length < K) {
//			BigDecimal len = new BigDecimal(A.length);
//			BigDecimal rotations = new BigDecimal(K);
//			BigDecimal newK = rotations.divide(len, RoundingMode.HALF_UP);
//			if(newK.intValue()>A.length)
//				for(;;) {
//					 newK = newK.divide(len, RoundingMode.HALF_UP);
//					 if(newK.intValue()<A.length)
//						 break;
//				}
//			K = newK.intValue();
			return tradditionalWay(A, K);
		}
		// if number Rotation == array size it mean the same array will return
		if (A.length == K)
			return A;
		// check if array is zeros so it will no any effect in any number of rotation
		int sum = 0;
		for (int i = 0; i < A.length; i++) {
			sum += A[i];
			if (sum > 0)
				break;
		}
		if (sum == 0)
			return A;

		// if array is even and k == to half array then we will get last portion at
		// first and first at last
		if (A.length == K / 2) {
			return devideHalfArray(A);
		}

		// go into any other case but we will not loop on all array we will cutt the end
		// of array
		int cutFromEndArray[] = new int[K];
		int cutFromBeginArray[] = new int[A.length - cutFromEndArray.length];
		for (int i = A.length - K, cuttedArrayIndex = 0; i < A.length; i++, cuttedArrayIndex++) {
			cutFromEndArray[cuttedArrayIndex] = A[i];
		}
		for (int i = 0; i < A.length - K; i++) {
			cutFromBeginArray[i] = A[i];
		}
		for (int i = 0, secondPortionIndex = 0, firstIndexPortionIndex = 0; i < A.length; i++) {
			if (secondPortionIndex < cutFromEndArray.length) {
				A[i] = cutFromEndArray[secondPortionIndex];
				secondPortionIndex++;
			} else if (firstIndexPortionIndex < cutFromBeginArray.length) {
				A[i] = cutFromBeginArray[firstIndexPortionIndex];
				firstIndexPortionIndex++;
			}
		}
		return A;
		// -------- if no of before cases applay tradditional way
//		return tradditionalWay(A);
	}

	private int[] devideHalfArray(int[] A) {
		int[] firstPortion = new int[A.length / 2];
		int[] secondPortion = new int[A.length / 2];
		for (int i = 0; i < A.length / 2; i++) {
			firstPortion[i] = A[i];
		}
		for (int i = A.length / 2, newIndex = 0; i < A.length; i++, newIndex++) {
			secondPortion[newIndex] = A[i];
		}
		for (int i = 0, secondPortionIndex = 0, firstIndexPortionIndex = 0; i < A.length; i++) {
			if (secondPortionIndex < secondPortion.length) {
				A[i] = secondPortion[secondPortionIndex];
				secondPortionIndex++;
			} else if (firstIndexPortionIndex < firstPortion.length) {
				A[i] = firstPortion[firstIndexPortionIndex];
				firstIndexPortionIndex++;
			}
		}
		return A;
	}

	private int[] tradditionalWay(int[] A, int K) {
		int globalVal = 0, newValue = 0;
		for (int times = 1; times <= K; times++)
			for (int i = 0; i < A.length; i++) {
				if (i == 0)
					globalVal = A[i];
				else if (i <= A.length - 2) {
					newValue = A[i];
					A[i]=globalVal;
					globalVal = newValue;
				} else if (i == A.length - 1) {
					A[0] = A[i];
					A[i] =globalVal;
				}
				

			}

		return A;
	}
}
