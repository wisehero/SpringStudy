package com.example.demo.job.FileDataReadWrite;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import com.example.demo.job.FileDataReadWrite.dto.Player;

public class PlayerFieldSetMapper implements FieldSetMapper<Player> {
	@Override
	public Player mapFieldSet(FieldSet fieldSet) throws BindException {
		Player player = new Player();

		player.setID(fieldSet.readString(0));
		player.setLastName(fieldSet.readString(1));
		player.setFirstName(fieldSet.readString(2));
		player.setPosition(fieldSet.readString(3));
		player.setBirthYear(fieldSet.readInt(4));
		player.setDebutYear(fieldSet.readInt(5));

		return player;
	}
}
