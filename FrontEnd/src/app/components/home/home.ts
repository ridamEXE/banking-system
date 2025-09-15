import { Component, AfterViewInit } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.html',
  styleUrls: ['./home.css']
})
export class HomeComponent implements AfterViewInit {

  ngAfterViewInit(): void {
    let currentSlide = 0;
    const slides = document.querySelectorAll<HTMLElement>('.slide');
    const dots = document.querySelectorAll<HTMLElement>('.dot');
    const slidesContainer = document.querySelector<HTMLElement>('.slides');

    function showSlide(index: number) {
      if (index >= slides.length) index = 0;
      if (index < 0) index = slides.length - 1;
      currentSlide = index;

      if (slidesContainer) {
        slidesContainer.style.transform = `translateX(-${currentSlide * 100}%)`;
      }

      dots.forEach(dot => dot.classList.remove('active'));
      dots[currentSlide].classList.add('active');
    }

    // Auto slide
    setInterval(() => {
      showSlide(currentSlide + 1);
    }, 2000);

    // Dots click
    dots.forEach((dot, idx) => {
      dot.addEventListener('click', () => showSlide(idx));
    });

    // Prev / Next buttons (if you have them in HTML)
    const prevBtn = document.querySelector<HTMLElement>('.prev');
    const nextBtn = document.querySelector<HTMLElement>('.next');

    if (prevBtn) {
      prevBtn.addEventListener('click', () => showSlide(currentSlide - 1));
    }
    if (nextBtn) {
      nextBtn.addEventListener('click', () => showSlide(currentSlide + 1));
    }

    // Initial load
    showSlide(0);
  }
}