import { Injectable } from '@angular/core';
import * as amqp from 'amqplib/callback_api';
import {Connection} from "amqplib/callback_api";
import express from 'express';

@Injectable({
  providedIn: 'root'
})
export class RabbitMqService {

  constructor() { }

  createConnection() {

    amqp.connect('amqp://guest:guest@localhost:15672',
      (error0, connection) => {
      if(error0) {
        throw error0;
      }
      connection.createChannel((error1, channel) => {
        if(error1) {
          throw error1;
        }

        const app = express();


      })
      })
  }

}
