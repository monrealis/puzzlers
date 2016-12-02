delta = 0.000000000001

class FractionSplit:
	def getSum(self, n, d):
		self.fractions = []
		self.fillFractions(1.0 * n / d)
		return self.fractions

	def fillFractions(self, remaining):
		while remaining > delta:
			c = self.getLargestDenominatorWithNominatorOne(remaining)
			remaining = remaining - 1.0 / c
			self.fractions.append('%d/%d' % (1, c))

	def getLargestDenominatorWithNominatorOne(self, upperBound):
		inverse = 1 / upperBound
		c = int(inverse)
		if (inverse - c > delta):
			return c + 1
		else:
			return c

