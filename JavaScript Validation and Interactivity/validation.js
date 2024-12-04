function validateForm() {
    const email = document.getElementById('email').value;
    const password = document.getElementById('password').value;
    const confirmPassword = document.getElementById('confirmPassword').value;
  
    // Email validation
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailRegex.test(email)) {
      document.getElementById('emailError').textContent = 'Invalid email format';
      return false;
    } else {
      document.getElementById('emailError').textContent = '';
    }
  
    // Password strength validation (basic)
    if (password.length < 8) {
      document.getElementById('passwordError').textContent = 'Password must be at least 8 characters';
      return false;
    } else {
      document.getElementById('passwordError').textContent = '';
    }
  
    // Password confirmation
    if (password !== confirmPassword) {
      document.getElementById('confirmPasswordError').textContent = 'Passwords do not match';
      return false;
    } else {
      document.getElementById('confirmPasswordError').textContent = '';
    }
  
    return true;
  }
  
  // Live input feedback for email
  document.getElementById('email').addEventListener('input', function() {
    const email = this.value;
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailRegex.test(email)) {
      this.classList.add('is-invalid');
      document.getElementById('emailError').textContent = 'Invalid email format';
    } else {
      this.classList.remove('is-invalid');
      document.getElementById('emailError').textContent = '';
    }
  });