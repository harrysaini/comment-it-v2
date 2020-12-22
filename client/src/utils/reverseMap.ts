import { forEachRight } from 'lodash';

export const reverseMap = <T>(arr: any[], cb: (val:T, index: number) => any) => {
  const results: any[] = [];

  forEachRight(arr, (val, index) => {
    results.push(cb(val, index))
  });

  return results;

};
