import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'rule',
  templateUrl: './rule.component.html',
  styleUrls: ['./rule.component.css'],
})
export class RuleComponent implements OnInit {
  @Input() rule: string = 'rule';
  constructor() {}

  ngOnInit(): void {}
}
