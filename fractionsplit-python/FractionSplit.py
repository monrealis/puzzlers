class FractionSplit:
	def getSum(self, n, d):
		fractions = [];
		a = 1.0 * n / d 
		delta = 0.0000000001
		while abs(a) > delta:
			c = int(1 /a)
			if (1 / a - int(1 / a) > delta):
				c += 1;			
			a = a - 1.0 / c
			fractions.append('%d/%d' % (1, c))
		return fractions
