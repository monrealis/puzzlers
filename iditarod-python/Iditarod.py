import re




class Iditarod:
	
	def avgMinutes(self, times):
		minutes = [];
		for time in times:
			minutes.append(self.toMinutes(time))
		div, mod = divmod(sum(minutes), minutes.__len__())
		if (mod * 2 >= minutes.__len__()):
			return div + 1
		else:
			return div;
	
	def toMinutes(self, time):
		print(time);
		parts = re.split('[ ,:]+', time)
		hour = int(parts[0]) % 12
		minute = int(parts[1])
		if (parts[2] == 'PM'):
			hour += 12
		hour += 24 * (int(parts[4]) - 1)
		hour -= 8
		# print(parts);
		# print(str(hour) + ' ' + str(minute))
		return hour * 60 + minute
	
	
	

