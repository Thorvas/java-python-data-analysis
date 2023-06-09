class Powiat:
    def __init__(self, name, population, estimation, last_updated):
        self.name = name
        self.population = population
        self.estimation = estimation
        self.last_updated = last_updated


    def asdict(self):
        return{'name': self.name, 'population': self.population, 'estimation': self.estimation, 'last_updated': self.last_updated}