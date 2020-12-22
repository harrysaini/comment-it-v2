import { production } from './production';
import { development } from './development';
import { extend } from 'lodash';

export interface IConfig{
  apiUrl: string;
  STOP_RECURSION_LEVEL: number;
}

let defaultConfig = {
  STOP_RECURSION_LEVEL: 5
};
let envConfig;

if(process.env.NODE_ENV === 'production') {
  envConfig =  production;
} else {
  envConfig = development;
}

export const config: IConfig = extend({}, envConfig, defaultConfig) ;
