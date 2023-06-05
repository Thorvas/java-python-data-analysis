from functions import *

class Voivodeship:
    def __init__(self, name, population):
        self.name = name
        self.population = population
        

voivodeships = []

for i in range(16):
    voivodeship = Voivodeship(get_voivodeship_name(i), get_voivodeship_population(i))
    voivodeships.append(voivodeship)
        