import { AvailableTimePeriod } from './available-time-period';
import { FishingEquipment } from './fishing-equipment-model';
import { Utility } from './utility-model';

export class Adventure {
  constructor(
    public id: number = 0,
    public name: string = '',
    public rating: number = 0,
    public price: number = 0,
    public navigationalEquipments: string[] = [],
    public address: string = '',
    public description: string = '',
    public guestLimit: number = 0,
    public rules: string[] = [],
    public utilities: Utility[] = [],
    public ownerName: string = '',
    public instructorBiography: string = '',
    public imageUrls: string[] = [],
    public fishingEquipments: FishingEquipment[] = [],
    public availableTimePeriods: AvailableTimePeriod[] = [],
    public ownerId: number = 0
  ) {}
}
