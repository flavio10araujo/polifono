package com.polifono.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.polifono.domain.ClassPlayer;
import com.polifono.domain.Player;
import com.polifono.repository.IClassPlayerRepository;
import com.polifono.service.IClassPlayerService;

@Service
public class ClassPlayerServiceImpl implements IClassPlayerService {

	private IClassPlayerRepository repository;
	
	@Autowired
	public ClassPlayerServiceImpl(IClassPlayerRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public final ClassPlayer save(ClassPlayer classPlayer) {
		return repository.save(classPlayer);
	}
	
	@Override
	public Boolean delete(Integer id) {
		ClassPlayer temp = repository.findOne(id);
		
		if (temp != null) {
			try {
				repository.save(prepareClassPlayerToChangeStatus(temp, 3));
			}
			catch (Exception e) {
				return false;
			}
			
			return true;
		}
		
		return false;
	}
	
	@Override
	public ClassPlayer findOne(int id) {
        return repository.findOne(id);
    }

	@Override
	public final List<ClassPlayer> findAll() {
		return (List<ClassPlayer>) repository.findAll();
	}
	
	/**
	 * Get all the students of this teacher (playerId).
	 * Get only student in the status 1 (pending) and 2 (confirmed). 
	 * 
	 * @param playerId
	 * @return
	 */
	@Override
	public final List<ClassPlayer> findByTeacher(int playerId) {
		return repository.findByTeacher(playerId);
	}
	
	/**
	 * Get all the students of a specific class (clazzId) of this teacher (playerId).
	 * Get only student in the status 1 (pending) and 2 (confirmed). 
	 * 
	 * @param playerId
	 * @param clazzId
	 * @return
	 */
	@Override
	public final List<ClassPlayer> findByTeacherAndClass(int playerId, int clazzId) {
		return repository.findByTeacherAndClass(playerId, clazzId);
	}
	
	@Override
	public final List<ClassPlayer> findByClassAndStatus(int clazzId, int status) {
		return repository.findByClassAndStatus(clazzId, status);
	}
	
	@Override
	public final List<ClassPlayer> findByClassAndPlayer(int clazzId, int playerId) {
		return repository.findByClassAndPlayer(clazzId, playerId);
	}
	
	@Override
	public final List<ClassPlayer> findByPlayerAndStatus(int playerId, int status) {
		return repository.findByPlayerAndStatus(playerId, status);
	}
	
	/**
	 * Method used to see if studentId is student of teacherId in any class.
	 * The studentId must be in status 2 (confirmed).
	 * 
	 * @param teacherId
	 * @param studentId
	 * @return
	 */
	@Override
	public final List<ClassPlayer> findByTeacherAndStudent(int teacherId, int studentId) {
		return repository.findByTeacherAndStudent(teacherId, studentId);
	}
	
	@Override
	public final ClassPlayer create(ClassPlayer classPlayer) {
		return repository.save(this.prepareClassPlayerForCreation(classPlayer));
	}
	
	/**
	 * Method used to see if student is student of teacher in any class.
	 * Return true if student is student of teacher.
	 * 
	 * @param teacher
	 * @param student
	 * @return
	 */
	@Override
	public boolean isMyStudent(Player teacher, Player student) {
		List<ClassPlayer> classPlayers = this.findByTeacherAndStudent(teacher.getId(), student.getId());
		
		if (classPlayers != null && classPlayers.size() > 0) {
			return true;
		}
		
		return false;
	}
	
	public final ClassPlayer prepareClassPlayerForCreation(ClassPlayer classPlayer) {
		classPlayer.setDtInc(new Date());
		classPlayer.setActive(true);
		classPlayer.setStatus(2);
		return classPlayer;
	}
	
	@Override
	public final Boolean changeStatus(int id, int status) {
		ClassPlayer temp = repository.findOne(id);
		
		if (temp != null) {
			try {
				repository.save(this.prepareClassPlayerToChangeStatus(temp, status));
			}
			catch (Exception e) {
				return false;
			}
			
			return true;
		}
		
		return false;
	}
	
	public final ClassPlayer prepareClassPlayerToChangeStatus(ClassPlayer classPlayer, int status) {
		classPlayer.setStatus(status);
		return classPlayer;
	}
}