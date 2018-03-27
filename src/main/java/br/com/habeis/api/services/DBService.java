package br.com.habeis.api.services;

import br.com.habeis.api.domain.Device;
import br.com.habeis.api.domain.Output;
import br.com.habeis.api.domain.Sensor;
import java.text.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DBService {

    @Autowired
    DeviceService deviceService;

    @Autowired
    OutputService outputService;

    @Autowired
    SensorService sensorService;

    public void instantiateTestDatabase() throws ParseException {

        Device device = new Device();
        device.setDescription("Equipamento #1");
        device.setName("Equipamento #1");
        device.setId(1);

        device = deviceService.create(device);

        Output output1 = new Output();
        output1.setDescription("Saída número #1");
        output1.setName("Saida #1");
        output1.setId(1);
        output1.setDevice(device);
        output1.setStatus(00);
        outputService.create(output1);

        Output output2 = new Output();
        output2.setDescription("Saída número #2");
        output2.setName("Saida #2");
        output2.setId(2);
        output2.setStatus(00);
        output2.setDevice(device);
        outputService.create(output2);

        Output output3 = new Output();
        output3.setDescription("Saída número #3");
        output3.setName("Saida #3");
        output3.setId(3);
        output3.setStatus(00);
        output3.setDevice(device);
        outputService.create(output3);

        Output output4 = new Output();
        output4.setDescription("Saída número #4");
        output4.setName("Saida #4");
        output4.setId(4);
        output4.setStatus(00);
        output4.setDevice(device);
        outputService.create(output4);

        Output output5 = new Output();
        output5.setDescription("Saída número #5");
        output5.setName("Saida #5");
        output5.setId(5);
        output5.setStatus(00);
        output5.setDevice(device);
        outputService.create(output5);

        Sensor sensor1 = new Sensor();
        sensor1.setDescription("Sensor número #1");
        sensor1.setName("Sensor #1");
        sensor1.setId(1);
        sensor1.setDevice(device);
        sensorService.create(sensor1);

        Sensor sensor2 = new Sensor();
        sensor2.setDescription("Sensor número #2");
        sensor2.setName("Sensor #2");
        sensor2.setId(2);
        sensor2.setDevice(device);
        sensorService.create(sensor2);

        Sensor sensor3 = new Sensor();
        sensor3.setDescription("Sensor número #3");
        sensor3.setName("Sensor #3");
        sensor3.setId(3);
        sensor3.setDevice(device);
        sensorService.create(sensor3);

        Sensor sensor4 = new Sensor();
        sensor4.setDescription("Sensor número #4");
        sensor4.setName("Sensor #4");
        sensor4.setId(4);
        sensor4.setDevice(device);
        sensorService.create(sensor4);

        Sensor sensor5 = new Sensor();
        sensor5.setDescription("Sensor número #5");
        sensor5.setName("Sensor #5");
        sensor5.setId(5);
        sensor5.setDevice(device);
        sensorService.create(sensor5);

        Sensor sensor6 = new Sensor();
        sensor6.setDescription("Sensor número #6");
        sensor6.setName("Sensor #6");
        sensor6.setId(6);
        sensor6.setDevice(device);
        sensorService.create(sensor6);

        Sensor sensor7 = new Sensor();
        sensor7.setDescription("Sensor número #7");
        sensor7.setName("Sensor #7");
        sensor7.setId(7);
        sensor7.setDevice(device);
        sensorService.create(sensor7);

        Sensor sensor8 = new Sensor();
        sensor8.setDescription("Sensor número #8");
        sensor8.setName("Sensor #8");
        sensor8.setId(8);
        sensor8.setDevice(device);
        sensorService.create(sensor8);

        Sensor sensor9 = new Sensor();
        sensor9.setDescription("Sensor número #9");
        sensor9.setName("Gaveta");
        sensor9.setId(9);
        sensor9.setDevice(device);
        sensorService.create(sensor9);

        Sensor sensor10 = new Sensor();
        sensor10.setDescription("Sensor número #10");
        sensor10.setName("Gaveta");
        sensor10.setId(10);
        sensor10.setDevice(device);
        sensorService.create(sensor10);

        Device device1 = new Device();
        device1.setDescription("Equipamento #2");
        device1.setName("Equipamento #1");
        device1.setId(1);

        device1 = deviceService.create(device1);

        Output output11 = new Output();
        output11.setDescription("Saída número #1");
        output11.setName("Saida #1");
        //output11.setId(1);
        output11.setDevice(device1);
        output11.setStatus(00);
        outputService.create(output11);

        Output output12 = new Output();
        output12.setDescription("Saída número #2");
        output12.setName("Saida #2");
        //output12.setId(2);
        output12.setStatus(00);
        output12.setDevice(device);
        outputService.create(output12);

        Output output13 = new Output();
        output13.setDescription("Saída número #3");
        output13.setName("Saida #3");
        //output13.setId(3);
        output13.setStatus(00);
        output13.setDevice(device1);
        outputService.create(output13);

        Output output14 = new Output();
        output14.setDescription("Saída número #4");
        output14.setName("Saida #4");
        //output14.setId(4);
        output14.setStatus(00);
        output14.setDevice(device1);
        outputService.create(output14);

        Output output15 = new Output();
        output15.setDescription("Saída número #5");
        output15.setName("Saida #5");
        //output15.setId(5);
        output15.setStatus(00);
        output15.setDevice(device1);
        outputService.create(output15);

        Sensor sensor11 = new Sensor();
        sensor11.setDescription("Sensor número #1");
        sensor11.setName("Sensor #1");
        //sensor11.setId(1);
        sensor11.setDevice(device1);
        sensorService.create(sensor11);

        Sensor sensor12 = new Sensor();
        sensor12.setDescription("Sensor número #2");
        sensor12.setName("Sensor #2");
        //sensor12.setId(2);
        sensor12.setDevice(device1);
        sensorService.create(sensor12);

        Sensor sensor13 = new Sensor();
        sensor13.setDescription("Sensor número #3");
        sensor13.setName("Sensor #3");
        //sensor13.setId(3);
        sensor13.setDevice(device1);
        sensorService.create(sensor13);

        Sensor sensor14 = new Sensor();
        sensor14.setDescription("Sensor número #4");
        sensor14.setName("Sensor #4");
        //sensor14.setId(4);
        sensor14.setDevice(device1);
        sensorService.create(sensor14);

        Sensor sensor15 = new Sensor();
        sensor15.setDescription("Sensor número #5");
        sensor15.setName("Sensor #5");
        //sensor15.setId(5);
        sensor15.setDevice(device1);
        sensorService.create(sensor15);

        Sensor sensor16 = new Sensor();
        sensor16.setDescription("Sensor número #6");
        sensor16.setName("Sensor #6");
        //sensor16.setId(6);
        sensor16.setDevice(device1);
        sensorService.create(sensor16);

        Sensor sensor17 = new Sensor();
        sensor17.setDescription("Sensor número #7");
        sensor17.setName("Sensor #7");
        //sensor17.setId(7);
        sensor17.setDevice(device1);
        sensorService.create(sensor17);

        Sensor sensor18 = new Sensor();
        sensor18.setDescription("Sensor número #8");
        sensor18.setName("Sensor #8");
        // sensor18.setId(8);
        sensor18.setDevice(device1);
        sensorService.create(sensor18);

        Sensor sensor19 = new Sensor();
        sensor19.setDescription("Sensor número #9");
        sensor19.setName("Gaveta");
        //sensor19.setId(9);
        sensor19.setDevice(device1);
        sensorService.create(sensor19);

        Sensor sensor110 = new Sensor();
        sensor110.setDescription("Sensor número #10");
        sensor110.setName("Gaveta");
        //sensor110.setId(10);
        sensor110.setDevice(device1);
        sensorService.create(sensor110);
    }
}
