import { Injectable } from '@angular/core';
import { server } from '../app-global';
import { Cottage } from '../model/cottage-model';
import { SearchFilter } from '../model/search-filter-model';
import { Utility } from '../model/utility-model';

@Injectable({
  providedIn: 'root',
})
export class SearchService {
  url = server + 'browse/cottages';

  constructor() {}

  matchName(string1: string, string2: string) {
    string1 = string1.toLowerCase().replace(/\s/g, '');
    string2 = string2.toLowerCase().replace(/\s/g, '');
    return string1.includes(string2) || string2 == '';
  }

  hasTag(tag: string, list: Utility[]) {
    let tagValidation = false;
    for (let utility of list) {
      if (utility.name == tag) return true;
    }
    return tagValidation;
  }

  filter(entities: any[], filter: SearchFilter) {
    var retVal: Cottage[] = [];

    for (let entity of entities) {
      if (
        this.matchName(entity.name, filter.text) ||
        this.matchName(entity.ownerName, filter.text) ||
        this.matchName(entity.address, filter.text)
      ) {
        let tagValidation = true;
        for (let tag of filter.tags) {
          if (!this.hasTag(tag, entity.utilities)) tagValidation = false;
        }

        if (tagValidation) retVal.push(entity);
      }
    }

    switch (filter.sort) {
      case 'PRICE_LOWER':
        return this.priceLower(retVal);
      case 'PRICE_HIGHER':
        return this.priceHigher(retVal);
      case 'RATING_HIGHER':
        return this.ratingHigher(retVal);
      case 'RATING_LOWER':
        return this.ratingLower(retVal);
      case 'NAME_ACS':
        return this.nameAcs(retVal);
      case 'NAME_DES':
        return this.nameDes(retVal);
      case 'ADDR_ACS':
        return this.addrAcs(retVal);
      case 'ADDR_DES':
        return this.addrDes(retVal);
      default:
        return retVal;
    }
  }

  priceHigher(entities: any[]) {
    return entities.sort((n1, n2) => {
      if (n1.price < n2.price) {
        return 1;
      } else {
        return -1;
      }
    });
  }

  priceLower(entities: any[]) {
    return entities.sort((n1, n2) => {
      if (n1.price > n2.price) {
        return 1;
      } else {
        return -1;
      }
    });
  }

  ratingLower(entities: any[]) {
    return entities.sort((n1, n2) => {
      if (n1.rating > n2.rating) {
        return 1;
      } else {
        return -1;
      }
    });
  }

  ratingHigher(entities: any[]) {
    return entities.sort((n1, n2) => {
      if (n1.rating < n2.rating) {
        return 1;
      } else {
        return -1;
      }
    });
  }

  nameAcs(entities: any[]) {
    return entities.sort((n1, n2) => {
      if (n1.name > n2.name) {
        return 1;
      } else {
        return -1;
      }
    });
  }

  nameDes(entities: any[]) {
    return entities.sort((n1, n2) => {
      if (n1.name < n2.name) {
        return 1;
      } else {
        return -1;
      }
    });
  }

  addrAcs(entities: any[]) {
    return entities.sort((n1, n2) => {
      if (n1.address > n2.address) {
        return 1;
      } else {
        return -1;
      }
    });
  }

  addrDes(entities: any[]) {
    return entities.sort((n1, n2) => {
      if (n1.address < n2.address) {
        return 1;
      } else {
        return -1;
      }
    });
  }
}
