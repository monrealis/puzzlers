import re




class Iditarod:
	def avgMinutes(self, times):
		minutes = self.toMinutesArray(times)
		div, mod = divmod(sum(minutes), minutes.__len__())
		if (mod * 2 >= minutes.__len__()):
			return div + 1
		else:
			return div;
	
	def toMinutesArray(self, times):
		minutes = []
		for time in times:
			minutes.append(self.toMinutesSinceStart(time))
		return minutes

	def toMinutesSinceStart(self, time):
		return self.toMinutes(time) - 8 * 60
	
	def toMinutes(self, time):
		parts = re.split('[ ,:]+', time)
		hour = int(parts[0]) % 12
		if (parts[2] == 'PM'):
			hour += 12
		minute = int(parts[1])
		day = int(parts[4]) - 1
		return day * 24 * 60 + hour * 60 + minute
	
	

