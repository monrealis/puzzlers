import Iditarod
import unittest

class IditarodTest(unittest.TestCase):

	def setUp(self):
		self.solution = Iditarod.Iditarod()

	def test_0(self):
		times = ['12:00 PM, DAY 1', '12:01 PM, DAY 1']

		expected = 241
		actual = self.solution.avgMinutes(times)

		self.assertEqual(expected, actual)

	def test_1(self):
		times = ['12:00 AM, DAY 2']

		expected = 960
		actual = self.solution.avgMinutes(times)

		self.assertEqual(expected, actual)

	def test_2(self):
		times = ['02:00 PM, DAY 19', '02:00 PM, DAY 20', '01:58 PM, DAY 20']

		expected = 27239
		actual = self.solution.avgMinutes(times)

		self.assertEqual(expected, actual)

if __name__ == '__main__': unittest.main()
