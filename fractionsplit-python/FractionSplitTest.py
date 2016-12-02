import FractionSplit
import unittest

class FractionSplitTest(unittest.TestCase):

	def setUp(self):
		self.solution = FractionSplit.FractionSplit()

	def test_0(self):
		n = 4
		d = 5

		expected = ['1/2', '1/4', '1/20']
		actual = self.solution.getSum(n, d)

		self.assertEqual(expected, actual)

	def test_1(self):
		n = 2
		d = 3

		expected = ['1/2', '1/6']
		actual = self.solution.getSum(n, d)

		self.assertEqual(expected, actual)

	def test_2(self):
		n = 1
		d = 2

		expected = ['1/2']
		actual = self.solution.getSum(n, d)

		self.assertEqual(expected, actual)

	def test_3(self):
		n = 15
		d = 16

		expected = ['1/2', '1/3', '1/10', '1/240']
		actual = self.solution.getSum(n, d)

		self.assertEqual(expected, actual)

	def test_4(self):
		n = 14
		d = 15

		expected = ['1/2', '1/3', '1/10']
		actual = self.solution.getSum(n, d)

		self.assertEqual(expected, actual)

if __name__ == '__main__': unittest.main()
