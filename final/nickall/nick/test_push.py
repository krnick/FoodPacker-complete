from gcm import *

gcm = GCM("AIzaSyBIUSF-fw975rn45jLEzS7aB_MxXqWvGjQ")
data = {'the_message': 'You have x new friends', 'param2': 'value2'}

reg_id = 'dQPP38mVN-k:APA91bGisdcT8nY6nnG3P1g_59mf5l2FpHovBfJPf11Z-Uk6o-gtxb3Xy8L6oYxSh2AKxwVu-xdyJYa5o9mfdWG_b7Tuk1v0sLV1s8Ivvw0vB7u-UcyvNJzKy94P0ngR03N05Mv5-0lF'

gcm.plaintext_request(registration_id=reg_id, data=data)
