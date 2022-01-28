package com.capgemini.ccsw.tutorial.author;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.capgemini.ccsw.tutorial.author.model.Author;
import com.capgemini.ccsw.tutorial.author.model.AuthorDto;
import com.capgemini.ccsw.tutorial.author.model.AuthorSearchDto;

/**
* @author ccsw
*/
@Service
@Transactional
public class AuthorServiceImpl implements AuthorService {

   @Autowired
   AuthorRepository authorRepository;

   /**
    * {@inheritDoc}
    */
   @Override
   public Author get(Long id) {

      return this.authorRepository.findById(id).orElse(null);
   }

   /**
   * {@inheritDoc}
   */
   @Override
   public Page<Author> findPage(AuthorSearchDto dto) {

      return this.authorRepository.findAll(dto.getPageable());
   }

   /**
   * {@inheritDoc}
   */
   @Override
   public void save(Long id, AuthorDto data) {

      Author author = null;
      if (id != null)
         author = this.authorRepository.findById(id).orElse(null);
      else
         author = new Author();

      BeanUtils.copyProperties(data, author, "id");

      this.authorRepository.save(author);
   }

   /**
   * {@inheritDoc}
   */
   @Override
   public void delete(Long id) {

      this.authorRepository.deleteById(id);

   }

   /**
   * {@inheritDoc}
   */
   @Override
   public List<Author> findAll() {

      return (List<Author>) this.authorRepository.findAll();
   }

}