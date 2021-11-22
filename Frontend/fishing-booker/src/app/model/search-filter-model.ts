export class SearchFilter {
  constructor(
    public text: string = '',
    public sort: string = 'NO_SORT',
    public tags: string[] = []
  ) {}
}
